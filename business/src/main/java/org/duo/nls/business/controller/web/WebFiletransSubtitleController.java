package org.duo.nls.business.controller.web;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.duo.nls.business.req.FiletransSubtitleQueryReq;
import org.duo.nls.business.req.GenSubtitleReq;
import org.duo.nls.business.req.GenTextReq;
import org.duo.nls.business.resp.CommonResp;
import org.duo.nls.business.resp.FiletransSubtitleQueryResp;
import org.duo.nls.business.resp.PageResp;
import org.duo.nls.business.service.FiletransSubtitleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/web/filetrans-subtitle")
public class WebFiletransSubtitleController {

    @Resource
    private FiletransSubtitleService filetransSubtitleService;

    @GetMapping("/query")
    public CommonResp<PageResp<FiletransSubtitleQueryResp>> query(@Valid FiletransSubtitleQueryReq req)  {
        PageResp<FiletransSubtitleQueryResp> pageResp = filetransSubtitleService.query(req);
        return new CommonResp<>(pageResp);
    }

    @GetMapping("/gen-subtitle")
    public CommonResp<String> genSubtitle(@Valid GenSubtitleReq req)  {
        String url = filetransSubtitleService.genSubtitle(req);
        return new CommonResp<>(url);
    }

    @GetMapping("/gen-text")
    public CommonResp<String> genText(@Valid GenTextReq req)  {
        String url = filetransSubtitleService.genText(req);
        return new CommonResp<>(url);
    }
}
