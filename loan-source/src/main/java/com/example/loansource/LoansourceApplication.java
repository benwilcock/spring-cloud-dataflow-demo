package com.example.loansource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
public class LoansourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansourceApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    void logStartEvent(){
        log.info("The Loan-source Application has started...");
    }

}
