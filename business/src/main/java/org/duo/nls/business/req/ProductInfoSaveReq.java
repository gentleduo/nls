package org.duo.nls.business.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductInfoSaveReq {

    private Long id;

    // String类型的数据，必须使用NotBlank
    @NotBlank(message = "【商品名称】不能为空")
    private String name;

    @NotBlank(message = "【商品分类】不能为空")
    private String category;
    
    private String tags;

    private String comment;
}
