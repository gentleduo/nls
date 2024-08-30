package org.duo.nls.business.controller.web;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.duo.nls.business.req.LabelInfoQueryReq;
import org.duo.nls.business.req.LabelInfoSaveReq;
import org.duo.nls.business.resp.CommonResp;
import org.duo.nls.business.resp.LabelInfoQueryResp;
import org.duo.nls.business.resp.PageResp;
import org.duo.nls.business.service.LabelInfoService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/web/label-info")
public class LabelInfoController {

    @Resource
    private LabelInfoService labelInfoService;

    @GetMapping("/query")
    public CommonResp<PageResp<LabelInfoQueryResp>> query(@Valid LabelInfoQueryReq req) {
        PageResp<LabelInfoQueryResp> pageResp = labelInfoService.query(req);
        return new CommonResp<>(pageResp);
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody LabelInfoSaveReq req) {
        CommonResp resp = new CommonResp<>();
        labelInfoService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        labelInfoService.delete(id);
        return resp;
    }
}
