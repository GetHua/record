package com.fern.record.controller;

import com.fern.record.entity.ItemGroup;
import com.fern.record.form.FindItemGroupForm;
import com.fern.record.form.ItemGroupForm;
import com.fern.record.services.ItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ItemGroupController {

    @Autowired
    private ItemGroupService itemGroupService;

    @GetMapping
    public Mono<Page<ItemGroup>> findAll(FindItemGroupForm form) {
        return itemGroupService.findAll(form);
    }

    @GetMapping("/{id}")
    public Mono<ItemGroup> findById(@PathVariable Long id) {
        return itemGroupService.findById(id);
    }

    @PostMapping
    public Mono<ItemGroup> save(ItemGroupForm form) {
        return itemGroupService.save(form);
    }

    @DeleteMapping("/{id}")
    public Mono<ItemGroup> delete(@PathVariable Long id) {
        return itemGroupService.deleteById(id);
    }

}
