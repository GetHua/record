package com.fern.record.controller;

import com.fern.record.RecordApplicationTests;
import com.fern.record.entity.User;
import com.fern.record.form.FindUserForm;
import com.fern.record.form.UserForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest extends RecordApplicationTests {

    @Autowired
    private UserController userController;

    @Test
    void findAll() {

        FindUserForm form = new FindUserForm();
        form.setPage(0);
        form.setPageSize(10);

        Mono<Page<User>> all = userController.findAll(form);

        StepVerifier.create(all)
                .assertNext(System.out::println)
                .verifyComplete();

    }

    @Test
    void findById() {

        Mono<User> findById = userController.save(getUserForm())
                .map(User::getId)
                .flatMap(userController::findById);

        StepVerifier.create(findById)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void save() {

        Mono<User> save = userController.save(getUserForm());

        StepVerifier.create(save)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void delete() {

        Mono<User> delete = userController.save(getUserForm())
                .map(User::getId)
                .flatMap(userController::delete);

        StepVerifier.create(delete)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    private UserForm getUserForm() {
        UserForm form = new UserForm();
        form.setGroupId(UUID.randomUUID().toString());
        form.setNickname("z");
        form.setUsername("123456");
        form.setPassword("aaa");
        return form;
    }
}