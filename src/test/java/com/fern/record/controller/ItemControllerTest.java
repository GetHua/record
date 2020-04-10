package com.fern.record.controller;

import com.fern.record.RecordApplicationTests;
import com.fern.record.entity.Item;
import com.fern.record.form.FindItemForm;
import com.fern.record.form.ItemForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemControllerTest extends RecordApplicationTests {

    @Autowired
    private ItemController itemController;

    @Test
    void findAll() {

        FindItemForm form = new FindItemForm();
        form.setPage(0);
        form.setPageSize(10);

        Mono<Page<Item>> all = itemController.findAll(form);

        StepVerifier.create(all)
                .assertNext(System.out::println)
                .verifyComplete();

    }

    @Test
    void findById() {

        ItemForm form = new ItemForm();
        form.setName(UUID.randomUUID().toString().replace("-", ""));

        Mono<Item> findById = itemController.save(form)
                .map(Item::getId)
                .flatMap(itemController::findById);

        StepVerifier.create(findById)
                .assertNext(System.out::println)
                .verifyComplete();

    }

    @Test
    void save() {
        ItemForm form = new ItemForm();
        form.setName("aaa");

        Mono<Item> save = itemController.save(form);

        StepVerifier.create(save)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void delete() {

        ItemForm form = new ItemForm();
        form.setName(UUID.randomUUID().toString().replace("-", ""));

        Mono<Item> delete = itemController.save(form)
                .map(Item::getId)
                .flatMap(itemController::delete);

        StepVerifier.create(delete)
                .assertNext(System.out::println)
                .verifyComplete();
    }
}