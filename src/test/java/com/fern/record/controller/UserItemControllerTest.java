package com.fern.record.controller;

import com.fern.record.RecordApplicationTests;
import com.fern.record.entity.UserItem;
import com.fern.record.form.FindUserItemForm;
import com.fern.record.form.UserItemForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserItemControllerTest extends RecordApplicationTests {

    @Autowired
    private UserItemController userGroupController;

    @Test
    void findAll() {

        FindUserItemForm form = new FindUserItemForm();
        form.setPage(0);
        form.setPageSize(10);

        Mono<Page<UserItem>> all = userGroupController.findAll(form);

        StepVerifier.create(all)
                .assertNext(System.out::println)
                .verifyComplete();

    }

    @Test
    void findById() {

        Mono<UserItem> findById = userGroupController.save(getUserItemForm())
                .map(UserItem::getId)
                .flatMap(userGroupController::findById);

        StepVerifier.create(findById)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void save() {

        Mono<UserItem> save = userGroupController.save(getUserItemForm());

        StepVerifier.create(save)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void delete() {

        Mono<UserItem> delete = userGroupController.save(getUserItemForm())
                .map(UserItem::getId)
                .flatMap(userGroupController::delete);

        StepVerifier.create(delete)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    private UserItemForm getUserItemForm() {
        UserItemForm form = new UserItemForm();
        form.setUserId(UUID.randomUUID().toString());
        form.setItemId(UUID.randomUUID().toString());
        return form;
    }
}