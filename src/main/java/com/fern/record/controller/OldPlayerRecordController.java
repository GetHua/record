package com.fern.record.controller;

import com.fern.record.entity.OldPlayerRecord;
import com.fern.record.form.FindOldPlayerRecordForm;
import com.fern.record.form.OldPlayerRecordForm;
import com.fern.record.services.OldPlayerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/oldPlayerRecord")
public class OldPlayerRecordController {


    @Autowired
    private OldPlayerRecordService oldPlayerRecordService;

    @GetMapping
    public Mono<Page<OldPlayerRecord>> findAll(@Valid FindOldPlayerRecordForm form) {
        return oldPlayerRecordService.findAll(form);
    }

    @GetMapping("/{id}")
    public Mono<OldPlayerRecord> findById(@PathVariable String id) {
        return oldPlayerRecordService.findById(id);
    }

    @PostMapping
    public Mono<OldPlayerRecord> save(@Valid OldPlayerRecordForm form) {
        return oldPlayerRecordService.save(form);
    }

    @DeleteMapping("/{id}")
    public Mono<OldPlayerRecord> delete(@PathVariable String id) {
        return oldPlayerRecordService.deleteById(id);
    }

}
