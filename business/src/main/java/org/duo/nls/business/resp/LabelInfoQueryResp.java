package org.duo.nls.business.resp;

import lombok.Data;

@Data
public class LabelInfoQueryResp {

    // 解决前后端交互Long类型精度丢失的问题，
    //@JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    // 解决前后端交互Long类型精度丢失的问题，
    //@JsonSerialize(using= ToStringSerializer.class)
    private Long parent;

    private String name;

    private Integer sort;

    private String comment;
}
