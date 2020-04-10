package com.fern.record.controller;

import com.fern.record.entity.UserGroup;
import com.fern.record.form.FindUserGroupForm;
import com.fern.record.form.UserGroupForm;
import com.fern.record.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController {


    @Autowired
    private UserGroupService userGroupService;

    @GetMapping
    public Mono<Page<UserGroup>> findAll(FindUserGroupForm form) {
        return userGroupService.findAll(form);
    }

    @GetMapping("/{id}")
    public Mono<UserGroup> findById(@PathVariable String id) {
        return userGroupService.findById(id);
    }

    @PostMapping
    public Mono<UserGroup> save(UserGroupForm form) {
        return userGroupService.save(form);
    }

    @DeleteMapping("/{id}")
    public Mono<UserGroup> delete(@PathVariable String id) {
        return userGroupService.deleteById(id);
    }

}
