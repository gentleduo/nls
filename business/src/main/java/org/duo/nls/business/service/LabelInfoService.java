package org.duo.nls.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.duo.nls.business.domain.LabelInfo;
import org.duo.nls.business.domain.LabelInfoExample;
import org.duo.nls.business.mapper.LabelInfoMapper;
import org.duo.nls.business.req.LabelInfoQueryReq;
import org.duo.nls.business.resp.LabelInfoQueryResp;
import org.duo.nls.business.resp.PageResp;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LabelInfoService {

    @Resource
    private LabelInfoMapper labelInfoMapper;


    public PageResp<LabelInfoQueryResp> query(LabelInfoQueryReq req) {

        LabelInfoExample labelInfoExample = new LabelInfoExample();
        LabelInfoExample.Criteria criteria = labelInfoExample.createCriteria();

        if (StrUtil.isNotBlank(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }

        labelInfoExample.setOrderByClause("id desc");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<LabelInfo> labelInfos = labelInfoMapper.selectByExample(labelInfoExample);
        // 构造分页返回信息
        PageResp<LabelInfoQueryResp> pageResp = new PageResp<>();
        // 获取分页信息，只需要总数
        PageInfo<LabelInfo> pageInfo = new PageInfo<>(labelInfos);
        pageResp.setTotal(pageInfo.getTotal());
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());
        // 获取当前分页列表内容
        List<LabelInfoQueryResp> labelInfoQueryRespList = BeanUtil.copyToList(labelInfos, LabelInfoQueryResp.class);
        pageResp.setList(labelInfoQueryRespList);

        return pageResp;
    }


}
