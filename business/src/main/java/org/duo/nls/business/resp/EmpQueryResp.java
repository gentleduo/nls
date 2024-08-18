package org.duo.nls.business.resp;

import lombok.Data;

import java.util.Date;

@Data
public class EmpQueryResp {
    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private Double sal;

    private Double comm;

    private Integer deptno;
}