package org.duo.nls.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.duo.nls.business.req.EmpQueryReq;
import org.duo.nls.business.resp.CommonResp;
import org.duo.nls.business.resp.EmpQueryResp;
import org.duo.nls.business.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Resource
    DemoService demoService;

    @RequestMapping("/hello")
    public String hello() {

        return "hello world111";
    }

    @RequestMapping("/count")
    public int count() {

        return demoService.count();
    }

    @RequestMapping("/query")
    public CommonResp query(@Valid  EmpQueryReq req) {

        CommonResp<List<EmpQueryResp>> listCommonResp = new CommonResp<>();
        List<EmpQueryResp> query = demoService.query(req);
        listCommonResp.setContent(query);
        return listCommonResp;
    }
}