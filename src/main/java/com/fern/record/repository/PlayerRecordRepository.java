package com.fern.record.repository;

import com.fern.record.entity.PlayerRecord;
import com.fern.record.type.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Repository
public interface PlayerRecordRepository extends ReactiveMongoRepository<PlayerRecord, String>, CustomizedPlayerRecordRepository {
}

interface CustomizedPlayerRecordRepository{

    Mono<Page<PlayerRecord>> findByPage(PageRequest page);

    Mono<PlayerRecord> changeStateById(String id, StatusEnum statusEnum);
}

class CustomizedPlayerRecordRepositoryImpl implements CustomizedPlayerRecordRepository {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    private MongoPageAgent<PlayerRecord> mongoPageAgent;

    @PostConstruct
    private void init() {
        mongoPageAgent = new MongoPageAgent<>(reactiveMongoTemplate, PlayerRecord.class);
    }

    @Override
    public Mono<Page<PlayerRecord>> findByPage(PageRequest page) {
        return mongoPageAgent.findPage(page);
    }

    @Override
    public Mono<PlayerRecord> changeStateById(String id, StatusEnum statusEnum) {
        return reactiveMongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(id)),
                Update.update("state", statusEnum.name()),
                FindAndModifyOptions.options().returnNew(true),
                PlayerRecord.class
        );
    }
}
