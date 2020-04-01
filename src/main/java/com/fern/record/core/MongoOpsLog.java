package com.fern.record.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@Log4j2
public class MongoOpsLog {

    @DomainEvents
    void domainEvent() {
        System.out.println("self domainEvent");
    }

    @AfterDomainEventPublication
    void afterDomainEventPublication() {
        System.out.println("self afterDomainEventPublication");
    }

}
