package com.fern.record.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OldPlayerRecordForm {

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

}
