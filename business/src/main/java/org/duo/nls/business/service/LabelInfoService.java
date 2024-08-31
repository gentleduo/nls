package org.duo.nls.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.duo.nls.business.domain.LabelInfo;
import org.duo.nls.business.domain.LabelInfoExample;
import org.duo.nls.business.mapper.LabelInfoMapper;
import org.duo.nls.business.req.LabelInfoQueryReq;
import org.duo.nls.business.req.LabelInfoSaveReq;
import org.duo.nls.business.resp.LabelInfoQueryResp;
import org.duo.nls.business.resp.PageResp;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class LabelInfoService {

    @Resource
    private LabelInfoMapper labelInfoMapper;

    public List<LabelInfoQueryResp> all() {

        LabelInfoExample labelInfoExample = new LabelInfoExample();
        LabelInfoExample.Criteria criteria = labelInfoExample.createCriteria();
        criteria.andDelFlagEqualTo("0");
        labelInfoExample.setOrderByClause("sort asc");
        List<LabelInfo> labelInfoQueryRespList = labelInfoMapper.selectByExample(labelInfoExample);
        // 列表复制
        List<LabelInfoQueryResp> list = BeanUtil.copyToList(labelInfoQueryRespList, LabelInfoQueryResp.class);
        return list;
    }

    public PageResp<LabelInfoQueryResp> query(LabelInfoQueryReq req) {

        LabelInfoExample labelInfoExample = new LabelInfoExample();
        LabelInfoExample.Criteria criteria = labelInfoExample.createCriteria();

        if (StrUtil.isNotBlank(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        criteria.andDelFlagEqualTo("0");
        labelInfoExample.setOrderByClause("sort asc");

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

    public void save(LabelInfoSaveReq req) {

        Date now = new Date();
        LabelInfo labelInfo = BeanUtil.copyProperties(req, LabelInfo.class);
        if (ObjectUtil.isEmpty(req.getId())) {
            // 新增
            labelInfo.setCreateTime(now);
            labelInfo.setUpdateTime(now);
            labelInfoMapper.insertSelective(labelInfo);
        } else {
            // 更新
            labelInfo.setUpdateTime(now);
            labelInfoMapper.updateByPrimaryKeySelective(labelInfo);
        }
    }

    public void delete(Long id) {
        LabelInfo labelInfo = new LabelInfo();
        labelInfo.setId(id);
        labelInfo.setDelFlag("1");
        labelInfoMapper.updateByPrimaryKeySelective(labelInfo);
    }

    public void delete(List<Long> ids) {

        LabelInfoExample labelInfoExample = new LabelInfoExample();
        LabelInfoExample.Criteria criteria = labelInfoExample.createCriteria();
        criteria.andIdIn(ids);
        LabelInfo labelInfo = new LabelInfo();
        labelInfo.setDelFlag("1");
        labelInfoMapper.updateByExampleSelective(labelInfo, labelInfoExample);
    }
}
