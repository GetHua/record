package com.fern.record.repository;

import com.fern.record.entity.UserItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserItemRepository extends ReactiveMongoRepository<UserItem, Long>, CustomizedUserItemRepository {
}

interface CustomizedUserItemRepository{

}

class CustomizedUserItemRepositoryImpl implements CustomizedUserItemRepository {

}
