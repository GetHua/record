package com.fern.record.controller;

import com.fern.record.RecordApplicationTests;
import com.fern.record.entity.UserGroup;
import com.fern.record.form.FindUserGroupForm;
import com.fern.record.form.UserGroupForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserGroupControllerTest extends RecordApplicationTests {

    @Autowired
    private UserGroupController userGroupController;

    @Test
    void findAll() {

        FindUserGroupForm form = new FindUserGroupForm();
        form.setPage(0);
        form.setPageSize(10);

        Mono<Page<UserGroup>> all = userGroupController.findAll(form);

        StepVerifier.create(all)
                .assertNext(System.out::println)
                .verifyComplete();

    }

    @Test
    void findById() {

        Mono<UserGroup> findById = userGroupController.save(getUserGroupForm())
                .map(UserGroup::getId)
                .flatMap(userGroupController::findById);

        StepVerifier.create(findById)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void save() {

        Mono<UserGroup> save = userGroupController.save(getUserGroupForm());

        StepVerifier.create(save)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void delete() {

        Mono<UserGroup> delete = userGroupController.save(getUserGroupForm())
                .map(UserGroup::getId)
                .flatMap(userGroupController::delete);

        StepVerifier.create(delete)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    private UserGroupForm getUserGroupForm() {
        UserGroupForm form = new UserGroupForm();
        form.setName(UUID.randomUUID().toString());
        return form;
    }
}