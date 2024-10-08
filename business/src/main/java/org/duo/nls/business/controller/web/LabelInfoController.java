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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/web/label-info")
public class LabelInfoController {

    @Resource
    private LabelInfoService labelInfoService;

    @GetMapping("/all")
    public CommonResp<List<LabelInfoQueryResp>> all() {
        CommonResp resp = new CommonResp<>();
        List<LabelInfoQueryResp> labelInfos = labelInfoService.all();
        return new CommonResp<>(labelInfos);
    }

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

    @DeleteMapping("/batch-delete/{ids}")
    public CommonResp batchDelete(@PathVariable String ids) {
        CommonResp resp = new CommonResp<>();
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        labelInfoService.delete(idList);
        return resp;
    }
}
