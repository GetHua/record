package com.fern.record.repository;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

@Log4j2
public class MongoPageAgent<T> {

    private ReactiveMongoTemplate reactiveMongoTemplate;
    private Class<T> entityClass;

    public MongoPageAgent(ReactiveMongoTemplate reactiveMongoTemplate, Class<T> entityClass) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.entityClass = entityClass;
    }

    public Mono<Page<T>> findPage(Query query, Pageable pageable) {
        return this.reactiveMongoTemplate
                .find(Query.of(query).with(pageable), this.entityClass)
                .collectList()
                .flatMap((r) -> this.reactiveMongoTemplate.count(query, this.entityClass)
                                .map((count) -> {
                                    log.info("findPageByQuery:[pageable:{},count:{}]", pageable, count);
                                    return new PageImpl(r, pageable, count);
                                })
                );
    }

    public Mono<Page<T>> findPage(Pageable pageable) {
        return this.findPage(new Query(), pageable);
    }

}
