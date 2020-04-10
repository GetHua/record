package com.fern.record.controller;

import com.fern.record.RecordApplicationTests;
import com.fern.record.entity.PlayerRecord;
import com.fern.record.form.FindPlayerRecordForm;
import com.fern.record.form.PlayerRecordForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlayerRecordControllerTest extends RecordApplicationTests {

    @Autowired
    private PlayerRecordController playerRecordController;

    @Test
    void findAll() {

        FindPlayerRecordForm form = new FindPlayerRecordForm();
        form.setPage(0);
        form.setPageSize(10);

        Mono<Page<PlayerRecord>> all = playerRecordController.findAll(form);

        StepVerifier.create(all)
                .assertNext(System.out::println)
                .verifyComplete();

    }

    @Test
    void findById() {

        Mono<PlayerRecord> findById = playerRecordController.save(getPlayerRecordForm())
                .map(PlayerRecord::getId)
                .flatMap(playerRecordController::findById);

        StepVerifier.create(findById)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void save() {

        Mono<PlayerRecord> save = playerRecordController.save(getPlayerRecordForm());

        StepVerifier.create(save)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void delete() {

        Mono<PlayerRecord> delete = playerRecordController.save(getPlayerRecordForm())
                .map(PlayerRecord::getId)
                .flatMap(playerRecordController::delete);

        StepVerifier.create(delete)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    private PlayerRecordForm getPlayerRecordForm() {
        PlayerRecordForm form = new PlayerRecordForm();
        form.setData(LocalDateTime.now());
        form.setItemId(UUID.randomUUID().toString().replace("-", ""));
        form.setAmount(Integer.toString(random.nextInt()));
        form.setAuthAmount(Integer.toString(random.nextInt()));
        form.setAuthActiveAmount(Integer.toString(random.nextInt()));
        form.setPaymentAmount(Integer.toString(random.nextInt()));
        return form;
    }
}