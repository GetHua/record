package com.fern.record.repository;

import com.fern.record.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Long>, ReactiveQuerydslPredicateExecutor<User>, CustomizedUserRepository {
}

interface CustomizedUserRepository{

}

class CustomizedUserRepositoryImpl implements CustomizedUserRepository {

}
