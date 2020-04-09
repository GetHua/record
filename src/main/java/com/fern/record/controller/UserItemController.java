package com.fern.record.controller;

import com.fern.record.services.UserItemService;
import com.fern.record.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserItemController {

    @Autowired
    private UserItemService userItemService;

}
