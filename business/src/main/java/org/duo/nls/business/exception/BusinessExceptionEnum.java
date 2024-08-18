package org.duo.nls.business.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BusinessExceptionEnum {

    DEPTNO_NOT_NULL("部门号不能为空!!!");

    @Getter
    private final String desc;
}
