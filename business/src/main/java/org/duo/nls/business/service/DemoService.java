package org.duo.nls.business.service;

import jakarta.annotation.Resource;
import org.duo.nls.business.domain.Emp;
import org.duo.nls.business.domain.EmpExample;
import org.duo.nls.business.enums.BusinessExceptionEnum;
import org.duo.nls.business.exception.BusinessException;
import org.duo.nls.business.mapper.EmpMapper;
import org.duo.nls.business.req.EmpQueryReq;
import org.duo.nls.business.resp.EmpQueryResp;
import org.duo.nls.business.util.CopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Resource
    private EmpMapper empMapper;

    public int count() {
        return Math.toIntExact(empMapper.countByExample(null));
    }

    public List<EmpQueryResp> query(EmpQueryReq req) {
        EmpExample empExample = new EmpExample();
        empExample.setOrderByClause("empno asc");
        EmpExample.Criteria criteria = empExample.createCriteria();
        if (req.getDeptno() == null) {
            throw new BusinessException(BusinessExceptionEnum.DEPTNO_NOT_NULL);
        }
        criteria.andDeptnoEqualTo(req.getDeptno());
        List<Emp> emps = empMapper.selectByExample(empExample);
        List<EmpQueryResp> empQueryResps = CopyUtil.copyList(emps, EmpQueryResp.class);
        return empQueryResps;
    }
}
