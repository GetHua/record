package com.fern.record.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OldPlayerRecordForm {

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

}
