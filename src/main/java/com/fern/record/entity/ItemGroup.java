package com.fern.record.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 项目组
 */
@Document
@Data
public class ItemGroup {

    @Id
    private String id;

    private String name;

    /**
     * @see com.fern.record.type.StatusEnum
     */
    private String state;

    @Version
    private Integer version;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateDate;
}
