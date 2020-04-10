package com.fern.record.services;

import com.fern.record.entity.UserGroup;
import com.fern.record.form.FindUserGroupForm;
import com.fern.record.form.UserGroupForm;
import com.fern.record.repository.UserGroupRepository;
import com.fern.record.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    public Mono<Page<UserGroup>> findAll(FindUserGroupForm form) {
        return userGroupRepository.findByPage(
                PageRequest.of(form.getPage(), form.getPageSize(), Sort.by("createDate").descending())
        );
    }

    public Mono<UserGroup> findById(Long id) {
        return Mono.just(id)
                .flatMap(userGroupRepository::findById);
    }

    public Mono<UserGroup> save(UserGroupForm form) {
        return Mono.justOrEmpty(form.getId())
                .flatMap(userGroupRepository::findById)
                .switchIfEmpty(Mono.fromCallable(UserGroup::new))
                .flatMap(item -> {
                    BeanUtils.copyProperties(form, item);
                    return userGroupRepository.save(item);
                });
    }

    public Mono<UserGroup> deleteById(Long id) {
        return changeStateById(id, StatusEnum.DELETED);
    }

    private Mono<UserGroup> changeStateById(Long id, StatusEnum statusEnum) {
        return Mono.justOrEmpty(id)
                .flatMap(itemId -> userGroupRepository.changeStateById(id, statusEnum));
    }

}
