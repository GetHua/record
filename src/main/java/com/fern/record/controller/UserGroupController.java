package com.fern.record.controller;

import com.fern.record.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserGroupController {


    @Autowired
    private UserGroupService userGroupService;

}
