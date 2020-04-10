package com.fern.record.services;

import com.fern.record.entity.PlayerRecord;
import com.fern.record.form.FindPlayerRecordForm;
import com.fern.record.form.PlayerRecordForm;
import com.fern.record.repository.PlayerRecordRepository;
import com.fern.record.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PlayerRecordService {

    @Autowired
    private PlayerRecordRepository playerRecordRepository;


    public Mono<Page<PlayerRecord>> findAll(FindPlayerRecordForm form) {
        return playerRecordRepository.findByPage(
                PageRequest.of(form.getPage(), form.getPageSize(), Sort.by("createDate").descending())
        );
    }

    public Mono<PlayerRecord> findById(String id) {
        return Mono.just(id)
                .flatMap(playerRecordRepository::findById);
    }

    public Mono<PlayerRecord> save(PlayerRecordForm form) {
        return Mono.justOrEmpty(form.getId())
                .flatMap(playerRecordRepository::findById)
                .switchIfEmpty(Mono.fromCallable(PlayerRecord::new))
                .flatMap(item -> {
                    BeanUtils.copyProperties(form, item);
                    return playerRecordRepository.save(item);
                });
    }

    public Mono<PlayerRecord> deleteById(String id) {
        return changeStateById(id, StatusEnum.DELETED);
    }

    private Mono<PlayerRecord> changeStateById(String id, StatusEnum statusEnum) {
        return Mono.justOrEmpty(id)
                .flatMap(itemId -> playerRecordRepository.changeStateById(id, statusEnum));
    }
}
