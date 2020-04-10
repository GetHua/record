package com.fern.record.services;

import com.fern.record.entity.User;
import com.fern.record.form.FindUserForm;
import com.fern.record.form.UserForm;
import com.fern.record.repository.UserRepository;
import com.fern.record.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public Mono<Page<User>> findAll(FindUserForm form) {
        return userRepository.findByPage(
                PageRequest.of(form.getPage(), form.getPageSize(), Sort.by("createDate").descending())
        );
    }

    public Mono<User> findById(Long id) {
        return Mono.just(id)
                .flatMap(userRepository::findById);
    }

    public Mono<User> save(UserForm form) {
        return Mono.justOrEmpty(form.getId())
                .flatMap(userRepository::findById)
                .switchIfEmpty(Mono.fromCallable(User::new))
                .flatMap(item -> {
                    BeanUtils.copyProperties(form, item);
                    return userRepository.save(item);
                });
    }

    public Mono<User> deleteById(Long id) {
        return changeStateById(id, StatusEnum.DELETED);
    }

    private Mono<User> changeStateById(Long id, StatusEnum statusEnum) {
        return Mono.justOrEmpty(id)
                .flatMap(itemId -> userRepository.changeStateById(id, statusEnum));
    }

}
