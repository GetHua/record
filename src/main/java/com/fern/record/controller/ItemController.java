package com.fern.record.controller;

import com.fern.record.entity.Item;
import com.fern.record.form.FindItemForm;
import com.fern.record.form.ItemForm;
import com.fern.record.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public Mono<Page<Item>> findAll(FindItemForm form) {
        return itemService.findAll(form);
    }

    @GetMapping("/{id}")
    public Mono<Item> findById(@PathVariable String id) {
        return itemService.findById(id);
    }

    @PostMapping
    public Mono<Item> save(ItemForm form) {
        return itemService.save(form);
    }

    @DeleteMapping("/{id}")
    public Mono<Item> delete(@PathVariable String id) {
        return itemService.deleteById(id);
    }
}
