package org.duo.nls.business.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EmpQueryReq {

    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private Double sal;

    private Double comm;

    @NotNull(message = "【部门号】不能为空")
    private Integer deptno;
}
