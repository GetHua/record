package com.fern.record.entity;

import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class User {

    @Id
    private Long id;
    private String username;
    private String nickname;
    private String headImg;
    private String password;

    @Version
    private Integer version;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateDate;
}
