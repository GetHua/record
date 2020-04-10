package com.fern.record.controller;

import com.fern.record.RecordApplicationTests;
import com.fern.record.entity.ItemGroup;
import com.fern.record.form.FindItemGroupForm;
import com.fern.record.form.ItemGroupForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

class ItemGroupControllerTest extends RecordApplicationTests {

    @Autowired
    private ItemGroupController itemGroupController;

    @Test
    void findAll() {

        FindItemGroupForm form = new FindItemGroupForm();
        form.setPage(0);
        form.setPageSize(10);

        Mono<Page<ItemGroup>> all = itemGroupController.findAll(form);

        StepVerifier.create(all)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void findById() {

        ItemGroupForm form = new ItemGroupForm();
        form.setName(UUID.randomUUID().toString().replace("-", ""));

        Mono<ItemGroup> itemGroupMono = itemGroupController.save(form)
                .map(ItemGroup::getId)
                .flatMap(itemGroupController::findById);

        StepVerifier.create(itemGroupMono)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void save() {

        ItemGroupForm form = new ItemGroupForm();
        form.setName(UUID.randomUUID().toString().replace("-", ""));

        Mono<ItemGroup> save = itemGroupController.save(form);

        StepVerifier.create(save)
                .assertNext(System.out::println)
                .verifyComplete();

    }

    @Test
    void delete() {

        ItemGroupForm form = new ItemGroupForm();
        form.setName(UUID.randomUUID().toString().replace("-", ""));

        Mono<ItemGroup> delete = itemGroupController.save(form)
                .map(ItemGroup::getId)
                .flatMap(itemGroupController::delete);

        StepVerifier.create(delete)
                .assertNext(System.out::println)
                .verifyComplete();

    }
}