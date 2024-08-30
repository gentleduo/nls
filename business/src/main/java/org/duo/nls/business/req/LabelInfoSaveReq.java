package org.duo.nls.business.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LabelInfoSaveReq {

    private Long id;

    @NotNull(message = "【父标签ID】不能为空")
    private Long parent;

    // String类型的数据，必须使用NotBlank
    @NotBlank(message = "【名称】不能为空")
    private String name;

    @NotNull(message = "【顺序】不能为空")
    private Integer sort;

    private String comment;
}
