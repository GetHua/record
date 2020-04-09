package com.fern.record.controller;

import com.fern.record.services.PlayerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerRecordController {

    @Autowired
    private PlayerRecordService playerRecordService;

}
