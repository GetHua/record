package com.fern.record.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 玩家每日数据
 */
@Document
@Data
public class PlayerRecord {

    @Id
    private String id;

    /** 项目id */
    private String itemId;

    /** 数据日期 */
    private LocalDateTime data;

    /** 玩家总数 */
    private String amount;

    /** 认证总数 */
    private String authAmount;

    /** 认证活跃总数 */
    private String authActiveAmount;

    /** 支付总额 */
    private String paymentAmount;

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
