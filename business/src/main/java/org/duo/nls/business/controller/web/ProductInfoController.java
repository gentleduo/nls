package org.duo.nls.business.controller.web;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.duo.nls.business.req.ProductInfoQueryReq;
import org.duo.nls.business.req.ProductInfoSaveReq;
import org.duo.nls.business.resp.CommonResp;
import org.duo.nls.business.resp.PageResp;
import org.duo.nls.business.resp.ProductInfoQueryResp;
import org.duo.nls.business.service.ProductInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/web/product-info")
public class ProductInfoController {

    @Resource
    private ProductInfoService productInfoService;

    @GetMapping("/query")
    public CommonResp<PageResp<ProductInfoQueryResp>> query(@Valid ProductInfoQueryReq req) {
        PageResp<ProductInfoQueryResp> pageResp = productInfoService.query(req);
        return new CommonResp<>(pageResp);
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody ProductInfoSaveReq req) {
        CommonResp resp = new CommonResp<>();
        productInfoService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        productInfoService.delete(id);
        return resp;
    }

    @DeleteMapping("/batch-delete/{ids}")
    public CommonResp batchDelete(@PathVariable String ids) {
        CommonResp resp = new CommonResp<>();
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        productInfoService.delete(idList);
        return resp;
    }
}
