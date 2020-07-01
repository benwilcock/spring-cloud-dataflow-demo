package com.example.loanprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(LoanProcessor.class)
public class LoanProcessing {

  private static final Long MAX_AMOUNT = 10000L;
  private LoanProcessor processor;

  @Autowired
  public LoanProcessing(LoanProcessor processor) {
    this.processor = processor;
  }

  @StreamListener(LoanProcessor.APPLICATIONS_IN)
  public void processLoanApplication(Loan loan) {
    log.info("Loan application {} from {} for ${} with id {}", loan.getStatus(), loan.getName(), loan.getAmount(), loan.getUuid());

    if (loan.getAmount() > MAX_AMOUNT) {
      loan.setStatus(Statuses.DECLINED.name());
      processor.declined().send(message(loan));
    } else {
      loan.setStatus(Statuses.APPROVED.name());
      processor.approved().send(message(loan));
    }

    log.info("Loan application {} from {} for ${} with id {}", loan.getStatus(), loan.getName(), loan.getAmount(), loan.getUuid());

  }

  private static final <T> Message<T> message(T val) {
    return MessageBuilder.withPayload(val).build();
  }
}
