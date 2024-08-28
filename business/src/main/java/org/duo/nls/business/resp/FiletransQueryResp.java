package org.duo.nls.business.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FiletransQueryResp {

    // 解决前后端交互Long类型精度丢失的问题，
    //@JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private Long memberId;

    private String name;

    private Integer second;

    private BigDecimal amount;

    private String audio;

    private String fileSign;

    private String payStatus;

    private String status;

    private String lang;

    private String vod;

    private String taskId;

    private Integer transStatusCode;

    private String transStatusText;

    private Date transTime;

    private Date solveTime;

    private Date createdAt;

    private Date updatedAt;
}
