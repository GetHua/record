package com.fern.record.repository;

import com.fern.record.entity.PlayerRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRecordRepository extends ReactiveMongoRepository<PlayerRecord, Long>, CustomizedPlayerRecordRepository {
}

interface CustomizedPlayerRecordRepository{

}

class CustomizedPlayerRecordRepositoryImpl implements CustomizedPlayerRecordRepository {

}
