package com.fern.record.repository;

import com.fern.record.entity.UserGroup;
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
public interface UserGroupRepository extends ReactiveMongoRepository<UserGroup, String>, CustomizedUserGroupRepository {
}

interface CustomizedUserGroupRepository{

    Mono<Page<UserGroup>> findByPage(PageRequest page);

    Mono<UserGroup> changeStateById(String id, StatusEnum statusEnum);
}

class CustomizedUserGroupRepositoryImpl implements CustomizedUserGroupRepository {


    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    private MongoPageAgent<UserGroup> mongoPageAgent;

    @PostConstruct
    private void init() {
        mongoPageAgent = new MongoPageAgent<>(reactiveMongoTemplate, UserGroup.class);
    }

    @Override
    public Mono<Page<UserGroup>> findByPage(PageRequest page) {
        return mongoPageAgent.findPage(page);
    }

    @Override
    public Mono<UserGroup> changeStateById(String id, StatusEnum statusEnum) {
        return reactiveMongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(id)),
                Update.update("state", statusEnum.name()),
                FindAndModifyOptions.options().returnNew(true),
                UserGroup.class
        );
    }
}
