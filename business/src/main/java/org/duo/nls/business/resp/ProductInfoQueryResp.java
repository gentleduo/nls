package org.duo.nls.business.resp;

import lombok.Data;

@Data
public class ProductInfoQueryResp {

    // 解决前后端交互Long类型精度丢失的问题，
    //@JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private String category;

    private String tags;

    private String comment;
}
