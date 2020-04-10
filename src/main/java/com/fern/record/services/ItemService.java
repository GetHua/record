package com.fern.record.services;

import com.fern.record.entity.Item;
import com.fern.record.form.FindItemForm;
import com.fern.record.form.ItemForm;
import com.fern.record.repository.ItemRepository;
import com.fern.record.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Mono<Page<Item>> findAll(FindItemForm form) {
        return itemRepository.findByPage(
                PageRequest.of(form.getPage(), form.getPageSize(), Sort.by("createDate").descending())
        );
    }

    public Mono<Item> findById(String id) {
        return Mono.just(id)
                .flatMap(itemRepository::findById);
    }

    public Mono<Item> save(ItemForm form) {
        return Mono.justOrEmpty(form.getId())
                .flatMap(itemRepository::findById)
                .switchIfEmpty(Mono.fromCallable(Item::new))
                .flatMap(item -> {
                    BeanUtils.copyProperties(form, item);
                    return itemRepository.save(item);
                });
    }

    public Mono<Item> deleteById(String id) {
        return changeStateById(id, StatusEnum.DELETED);
    }

    private Mono<Item> changeStateById(String id, StatusEnum statusEnum) {
        return Mono.justOrEmpty(id)
                .flatMap(itemId -> itemRepository.changeStateById(id, statusEnum));
    }
}
