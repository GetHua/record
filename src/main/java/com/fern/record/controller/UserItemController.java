package com.fern.record.controller;

import com.fern.record.entity.UserItem;
import com.fern.record.form.FindUserItemForm;
import com.fern.record.form.UserItemForm;
import com.fern.record.services.UserItemService;
import com.fern.record.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserItemController {

    @Autowired
    private UserItemService userItemService;

    @GetMapping
    public Mono<Page<UserItem>> findAll(FindUserItemForm form) {
        return userItemService.findAll(form);
    }

    @GetMapping("/{id}")
    public Mono<UserItem> findById(@PathVariable Long id) {
        return userItemService.findById(id);
    }

    @PostMapping
    public Mono<UserItem> save(UserItemForm form) {
        return userItemService.save(form);
    }

    @DeleteMapping("/{id}")
    public Mono<UserItem> delete(@PathVariable Long id) {
        return userItemService.deleteById(id);
    }
}
