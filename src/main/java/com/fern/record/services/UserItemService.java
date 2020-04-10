package com.fern.record.services;

import com.fern.record.entity.UserItem;
import com.fern.record.form.FindUserItemForm;
import com.fern.record.form.UserItemForm;
import com.fern.record.repository.UserItemRepository;
import com.fern.record.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserItemService {

    @Autowired
    private UserItemRepository userItemRepository;

    public Mono<Page<UserItem>> findAll(FindUserItemForm form) {
        return userItemRepository.findByPage(
                PageRequest.of(form.getPage(), form.getPageSize(), Sort.by("createDate").descending())
        );
    }

    public Mono<UserItem> findById(Long id) {
        return Mono.just(id)
                .flatMap(userItemRepository::findById);
    }

    public Mono<UserItem> save(UserItemForm form) {
        return Mono.justOrEmpty(form.getId())
                .flatMap(userItemRepository::findById)
                .switchIfEmpty(Mono.fromCallable(UserItem::new))
                .flatMap(item -> {
                    BeanUtils.copyProperties(form, item);
                    return userItemRepository.save(item);
                });
    }

    public Mono<UserItem> deleteById(Long id) {
        return changeStateById(id, StatusEnum.DELETED);
    }

    private Mono<UserItem> changeStateById(Long id, StatusEnum statusEnum) {
        return Mono.justOrEmpty(id)
                .flatMap(itemId -> userItemRepository.changeStateById(id, statusEnum));
    }

}
