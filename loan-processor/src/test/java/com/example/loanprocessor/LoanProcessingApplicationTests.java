package com.example.loanprocessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanProcessingApplicationTests {

  @Autowired
  @Qualifier("input")
  private MessageChannel inputChannel;

  @Autowired
  @Qualifier("approved")
  private MessageChannel approvedChannel;

  @Autowired
  @Qualifier("declined")
  private MessageChannel declinedChannel;

  @Autowired
  private MessageCollector collector;

  @Test
  public void contextLoads() {
  }

  @Test
  public void testApprovedLoanApplication() throws Exception {
    Loan approved = new Loan(UUID.randomUUID().toString(), "Ben", 9000);
    Message message = MessageBuilder.withPayload(approved).build();
    this.inputChannel.send(message);

    message = this.collector.forChannel(this.approvedChannel).poll(1, TimeUnit.SECONDS);
    validateMessage(message);

    assertTrue(this.collector.forChannel(this.declinedChannel).isEmpty());
  }

  @Test
  public void testDeniedLoanApplication() throws Exception {
    Loan approved = new Loan(UUID.randomUUID().toString(), "Ben", 99000);
    Message message = MessageBuilder.withPayload(approved).build();
    this.inputChannel.send(message);

    message = this.collector.forChannel(this.declinedChannel).poll(1, TimeUnit.SECONDS);
    validateMessage(message);
    assertTrue(this.collector.forChannel(this.approvedChannel).isEmpty());
  }

  private static void validateMessage(Message message) {
    String jsonMessage = message.getPayload().toString();
    assertTrue(jsonMessage.contains("uuid"));
    assertTrue(jsonMessage.contains("name"));
    assertTrue(jsonMessage.contains("amount"));
  }

}
