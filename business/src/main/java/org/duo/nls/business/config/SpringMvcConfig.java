package org.duo.nls.business.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer配置类：添加自定义拦截器，消息转换器等
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /*
     * CORS全局配置
     *
     * 实现WebMvcConfigurer接口，然后实现接口中的addCorsMappings方法。
     * 在addCorsMappings方法中，
     * addMapping表示对哪些格式的请求路径进行跨域处理；
     * allowedOriginPatterns表示支持的域；("*"表示支持所有域)
     * allowedHeaders表示允许的请求头，*表示所有的请求头都被允许，默认允许所有的请求头信息；
     * allowedMethods表示允许的请求方法，默认是GET、POST和HEAD；*表示支持所有的请求方法；
     * allowCredentials：允许请求携带cookie；
     * maxAge表示探测请求的有效期；对于DELETE、PUT或者自定义头信息的请求，在执行过程中会先发送探测请求，探测请求不用每次都发送，可以配置一个有效期
     * ，有效期过了之后才会重新发送探测请求。 这个属性默认是1800秒，即30分钟。
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }
}
