package com.fern.record.repository;

import com.fern.record.entity.UserGroup;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends ReactiveMongoRepository<UserGroup, Long>, CustomizedUserGroupRepository {
}

interface CustomizedUserGroupRepository{

}

class CustomizedUserGroupRepositoryImpl implements CustomizedUserGroupRepository {

}
