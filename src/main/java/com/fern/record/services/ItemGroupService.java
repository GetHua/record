package com.fern.record.services;

import com.fern.record.entity.ItemGroup;
import com.fern.record.form.FindItemGroupForm;
import com.fern.record.form.ItemGroupForm;
import com.fern.record.repository.ItemGroupRepository;
import com.fern.record.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ItemGroupService {

    @Autowired
    private ItemGroupRepository itemGroupRepository;

    public Mono<Page<ItemGroup>> findAll(FindItemGroupForm form) {
        return itemGroupRepository.findByPage(
                PageRequest.of(form.getPage(), form.getPageSize(), Sort.by("createDate").descending())
        );
    }

    public Mono<ItemGroup> findById(String id) {
        return Mono.just(id)
                .flatMap(itemGroupRepository::findById);
    }

    public Mono<ItemGroup> save(ItemGroupForm form) {
        return Mono.justOrEmpty(form.getId())
                .flatMap(itemGroupRepository::findById)
                .switchIfEmpty(Mono.fromCallable(ItemGroup::new))
                .flatMap(item -> {
                    BeanUtils.copyProperties(form, item);
                    return itemGroupRepository.save(item);
                });
    }

    public Mono<ItemGroup> deleteById(String id) {
        return changeStateById(id, StatusEnum.DELETED);
    }

    private Mono<ItemGroup> changeStateById(String id, StatusEnum statusEnum) {
        return Mono.justOrEmpty(id)
                .flatMap(itemId -> itemGroupRepository.changeStateById(id, statusEnum));
    }
}
