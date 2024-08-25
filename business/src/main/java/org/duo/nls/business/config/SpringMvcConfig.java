package org.duo.nls.business.config;

import jakarta.annotation.Resource;
import org.duo.nls.business.interceptor.AdminLoginInterceptor;
import org.duo.nls.business.interceptor.LogInterceptor;
import org.duo.nls.business.interceptor.WebLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    private LogInterceptor logInterceptor;

    @Resource
    private WebLoginInterceptor webLoginInterceptor;

    @Resource
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);

        // 路径不要包含context-path
        registry.addInterceptor(webLoginInterceptor)
                //.addPathPatterns("/web/**")
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/web/kaptcha/image-code/*",
                        "/web/member/login",
                        "/web/member/register",
                        "/web/member/reset",
                        "/web/sms-code/send-for-register",
                        "/web/sms-code/send-for-reset"
                );

        // 路径不要包含context-path
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(
                        "/admin/kaptcha/image-code/*",
                        "/admin/user/login",
                        "/admin/report/**"
                );
    }
}
