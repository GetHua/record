package com.fern.record.services;

import com.fern.record.repository.PlayerRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerRecordService {

    @Autowired
    private PlayerRecordRepository playerRecordRepository;

}
