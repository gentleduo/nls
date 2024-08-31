package org.duo.nls.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.duo.nls.business.domain.ProductInfo;
import org.duo.nls.business.domain.ProductInfoExample;
import org.duo.nls.business.mapper.ProductInfoMapper;
import org.duo.nls.business.req.ProductInfoQueryReq;
import org.duo.nls.business.req.ProductInfoSaveReq;
import org.duo.nls.business.resp.PageResp;
import org.duo.nls.business.resp.ProductInfoQueryResp;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductInfoService {

    @Resource
    private ProductInfoMapper productInfoMapper;


    public PageResp<ProductInfoQueryResp> query(ProductInfoQueryReq req) {

        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = productInfoExample.createCriteria();

        if (StrUtil.isNotBlank(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        criteria.andDelFlagEqualTo("0");
        productInfoExample.setOrderByClause("update_time desc");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(productInfoExample);
        // 构造分页返回信息
        PageResp<ProductInfoQueryResp> pageResp = new PageResp<>();
        // 获取分页信息，只需要总数
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfos);
        pageResp.setTotal(pageInfo.getTotal());
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());
        // 获取当前分页列表内容
        List<ProductInfoQueryResp> productInfoQueryRespList = BeanUtil.copyToList(productInfos, ProductInfoQueryResp.class);
        pageResp.setList(productInfoQueryRespList);

        return pageResp;
    }

    public void save(ProductInfoSaveReq req) {

        Date now = new Date();
        ProductInfo productInfo = BeanUtil.copyProperties(req, ProductInfo.class);
        productInfo.setTags(req.getTags().stream().collect(Collectors.joining(",")));
        if (ObjectUtil.isEmpty(req.getId())) {
            // 新增
            productInfo.setCreateTime(now);
            productInfo.setUpdateTime(now);
            productInfoMapper.insertSelective(productInfo);
        } else {
            // 更新
            productInfo.setUpdateTime(now);
            productInfoMapper.updateByPrimaryKeySelective(productInfo);
        }
    }

    public void delete(Long id) {
//        LabelInfo labelInfo = new LabelInfo();
//        labelInfo.setId(id);
//        labelInfo.setDelFlag("1");
//        productInfoMapper.updateByPrimaryKeySelective(labelInfo);
    }

    public void delete(List<Long> ids) {

//        LabelInfoExample labelInfoExample = new LabelInfoExample();
//        LabelInfoExample.Criteria criteria = labelInfoExample.createCriteria();
//        criteria.andIdIn(ids);
//        LabelInfo labelInfo = new LabelInfo();
//        labelInfo.setDelFlag("1");
//        productInfoMapper.updateByExampleSelective(labelInfo, labelInfoExample);
    }
}
