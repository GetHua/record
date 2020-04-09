package com.fern.record.services;

import com.fern.record.entity.OldPlayerRecord;
import com.fern.record.form.FindOldPlayerRecordForm;
import com.fern.record.form.OldPlayerRecordForm;
import com.fern.record.repository.OldPlayerRecordRepository;
import com.fern.record.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OldPlayerRecordService {

    @Autowired
    private OldPlayerRecordRepository oldPlayerRecordRepository;

    public Mono<Page<OldPlayerRecord>> findAll(FindOldPlayerRecordForm form) {
        return oldPlayerRecordRepository.findByPage(
                PageRequest.of(form.getPage(), form.getPageSize(), Sort.by("createDate").descending())
        );
    }

    public Mono<OldPlayerRecord> findById(Long id) {
        return Mono.just(id)
                .flatMap(oldPlayerRecordRepository::findById);
    }

    public Mono<OldPlayerRecord> save(OldPlayerRecordForm form) {
        return Mono.justOrEmpty(form.getId())
                .flatMap(oldPlayerRecordRepository::findById)
                .switchIfEmpty(Mono.fromCallable(OldPlayerRecord::new))
                .flatMap(item -> {
                    BeanUtils.copyProperties(form, item);
                    return oldPlayerRecordRepository.save(item);
                });
    }

    public Mono<OldPlayerRecord> deleteById(Long id) {
        return changeStateById(id, StatusEnum.DELETED);
    }

    private Mono<OldPlayerRecord> changeStateById(Long id, StatusEnum statusEnum) {
        return Mono.justOrEmpty(id)
                .flatMap(itemId -> oldPlayerRecordRepository.changeStateById(id, statusEnum));
    }
}
