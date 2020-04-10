package com.fern.record.controller;

import com.fern.record.RecordApplicationTests;
import com.fern.record.entity.OldPlayerRecord;
import com.fern.record.form.FindOldPlayerRecordForm;
import com.fern.record.form.OldPlayerRecordForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OldPlayerRecordControllerTest extends RecordApplicationTests {

    @Autowired
    private OldPlayerRecordController oldPlayerRecordController;

    @Test
    void findAll() {

        FindOldPlayerRecordForm form = new FindOldPlayerRecordForm();
        form.setPage(0);
        form.setPageSize(10);

        Mono<Page<OldPlayerRecord>> all = oldPlayerRecordController.findAll(form);

        StepVerifier.create(all)
                .assertNext(System.out::println)
                .verifyComplete();

    }

    @Test
    void findById() {

        Mono<OldPlayerRecord> findById = oldPlayerRecordController.save(getOldPlayerRecordForm())
                .map(OldPlayerRecord::getId)
                .flatMap(oldPlayerRecordController::findById);

        StepVerifier.create(findById)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void save() {

        Mono<OldPlayerRecord> save = oldPlayerRecordController.save(getOldPlayerRecordForm());

        StepVerifier.create(save)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    @Test
    void delete() {

        Mono<OldPlayerRecord> delete = oldPlayerRecordController.save(getOldPlayerRecordForm())
                .map(OldPlayerRecord::getId)
                .flatMap(oldPlayerRecordController::delete);

        StepVerifier.create(delete)
                .assertNext(System.out::println)
                .verifyComplete();
    }

    private OldPlayerRecordForm getOldPlayerRecordForm() {
        OldPlayerRecordForm form = new OldPlayerRecordForm();
        form.setData(LocalDateTime.now());
        form.setItemId(UUID.randomUUID().toString().replace("-", ""));
        form.setAmount(Integer.toString(random.nextInt()));
        form.setAuthAmount(Integer.toString(random.nextInt()));
        form.setAuthActiveAmount(Integer.toString(random.nextInt()));
        form.setPaymentAmount(Integer.toString(random.nextInt()));
        return form;
    }
}