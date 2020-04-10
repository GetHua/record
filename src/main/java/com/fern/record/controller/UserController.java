package com.fern.record.controller;

import com.fern.record.entity.User;
import com.fern.record.form.FindUserForm;
import com.fern.record.form.UserForm;
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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Mono<Page<User>> findAll(FindUserForm form) {
        return userService.findAll(form);
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public Mono<User> save(UserForm form) {
        return userService.save(form);
    }

    @DeleteMapping("/{id}")
    public Mono<User> delete(@PathVariable Long id) {
        return userService.deleteById(id);
    }

}
