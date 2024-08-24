package org.duo.nls.business.controller.web;

import cn.hutool.crypto.digest.DigestUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.duo.nls.business.enums.SmsCodeUseEnum;
import org.duo.nls.business.req.MemberLoginReq;
import org.duo.nls.business.req.MemberRegisterReq;
import org.duo.nls.business.req.MemberResetReq;
import org.duo.nls.business.resp.CommonResp;
import org.duo.nls.business.resp.MemberLoginResp;
import org.duo.nls.business.service.KaptchaService;
import org.duo.nls.business.service.MemberLoginLogService;
import org.duo.nls.business.service.MemberService;
import org.duo.nls.business.service.SmsCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/web/member")
public class WebMemberController {

    @Resource
    private MemberService memberService;

    @Resource
    private SmsCodeService smsCodeService;

    @Resource
    private KaptchaService kaptchaService;

    @Resource
    private MemberLoginLogService memberLoginLogService;

    @PostMapping("/register")
    public CommonResp<Object> register(@Valid @RequestBody MemberRegisterReq req) {
        req.setPassword(DigestUtil.md5Hex(req.getPassword().toLowerCase()));

        log.info("会员注册开始：{}", req.getMobile());

        smsCodeService.validCode(req.getMobile(), SmsCodeUseEnum.REGISTER.getCode(), req.getCode());
        log.info("注册验证码校验通过：{}", req.getMobile());

        memberService.register(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        req.setPassword(DigestUtil.md5Hex(req.getPassword().toLowerCase()));

        log.info("会员登录开始：{}", req.getMobile());

        // 校验图片验证码，防止机器人
        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());

        MemberLoginResp memberLoginResp = memberService.login(req);

        return new CommonResp<>(memberLoginResp);
    }

    @PostMapping("/reset")
    public CommonResp<Object> reset(@Valid @RequestBody MemberResetReq req) {
        req.setPassword(DigestUtil.md5Hex(req.getPassword().toLowerCase()));

        log.info("会员忘记密码开始：{}", req.getMobile());

        smsCodeService.validCode(req.getMobile(), SmsCodeUseEnum.RESET.getCode(), req.getCode());
        log.info("忘记密码验证码校验通过：{}", req.getMobile());

        memberService.reset(req);
        return new CommonResp<>();
    }

//    @GetMapping("/heart")
//    public CommonResp<Object> heart() {
//        memberLoginLogService.updateHeartInfo();
//        return new CommonResp<>();
//    }
}
