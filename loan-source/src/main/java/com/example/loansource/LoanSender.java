package com.example.loansource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@EnableScheduling
@EnableBinding(Source.class)
public class LoanSender {

    private Source source;
    private List<String> names = Arrays.asList("Donald", "Boris", "Vladimir", "Angela", "Emmanuel", "Shinzō", "Jacinda", "Kim");
    private List<Long> amounts = Arrays.asList(10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 100000000L);

    @Autowired
    public LoanSender(Source source) {
        this.source = source;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendEvents() {
        Loan loan = new Loan(UUID.randomUUID().toString(),
                names.get(new Random().nextInt(names.size())),
                amounts.get(new Random().nextInt(amounts.size())));
        log.info("Sending loan application for {} of ${} with id {}", loan.getName(), loan.getAmount(), loan.getUuid());
        this.source.output().send(MessageBuilder.withPayload(loan).build());
    }
}
