package com.fern.record.controller;

import com.fern.record.entity.PlayerRecord;
import com.fern.record.form.FindPlayerRecordForm;
import com.fern.record.form.PlayerRecordForm;
import com.fern.record.services.PlayerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PlayerRecordController {

    @Autowired
    private PlayerRecordService playerRecordService;

    @GetMapping
    public Mono<Page<PlayerRecord>> findAll(FindPlayerRecordForm form) {
        return playerRecordService.findAll(form);
    }

    @GetMapping("/{id}")
    public Mono<PlayerRecord> findById(@PathVariable Long id) {
        return playerRecordService.findById(id);
    }

    @PostMapping
    public Mono<PlayerRecord> save(PlayerRecordForm form) {
        return playerRecordService.save(form);
    }

    @DeleteMapping("/{id}")
    public Mono<PlayerRecord> delete(@PathVariable Long id) {
        return playerRecordService.deleteById(id);
    }

}
