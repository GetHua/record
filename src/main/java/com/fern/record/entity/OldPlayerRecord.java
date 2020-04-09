package com.fern.record.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 玩家历史数据
 * 原则上 一个项目只能有一条历史数据， 因为没有对方系统数据库
 */
@Document
@Data
public class OldPlayerRecord {

    @Id
    private Long id;

    /** 项目id */
    private Long itemId;

    /** 数据日期 */
    private LocalDateTime data;

    /** 玩家总数 */
    private Long amount;

    /** 认证总数 */
    private Long authAmount;

    /** 认证活跃总数 */
    private Long authActiveAmount;

    /** 支付总额 */
    private Long paymentAmount;

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
