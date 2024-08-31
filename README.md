# DDL

```sql
create database if not exists nls_db default character set utf8mb4 collate utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `EMPNO` int(4) NOT NULL AUTO_INCREMENT,
  `ENAME` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MGR` int(4) NULL DEFAULT NULL,
  `HIREDATE` date NULL DEFAULT NULL,
  `SAL` double(7, 2) NULL DEFAULT NULL,
  `COMM` double(7, 2) NULL DEFAULT NULL,
  `DEPTNO` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`EMPNO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


INSERT INTO `emp` VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800.00, NULL, 20);
INSERT INTO `emp` VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.00, 300.00, 30);
INSERT INTO `emp` VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250.00, 500.00, 30);
INSERT INTO `emp` VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975.00, NULL, 20);
INSERT INTO `emp` VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250.00, 1400.00, 30);
INSERT INTO `emp` VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.00, NULL, 30);
INSERT INTO `emp` VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450.00, NULL, 10);
INSERT INTO `emp` VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000.00, NULL, 10);
INSERT INTO `emp` VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.00, 0.00, 30);
INSERT INTO `emp` VALUES (7876, 'ADAMS', 'CLERK', 7788, '1987-05-23', 1100.00, NULL, 20);
INSERT INTO `emp` VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.00, NULL, 30);
INSERT INTO `emp` VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300.00, NULL, 10);
```

# 雪花算法

![image](doc\SnowFlake.png)

正常的时间戳=当前时间-基准时间1970年，这里的时间戳可以设置基准时间为任意年，让时间戳变小些

时间范围

单位：毫秒；365 * 24 * 60 * 60 * 1000L表示一年的毫秒数，整个公式表示：69.73年后会出现重复ID，

工作进程数

不同的机器（或者进程）；一般分成2部分，前5位表示数据中心占用的位数，后5为表示机器标识占用的位数；例如：如果只有一个数据中心的话，做多可以支持1024台机器，

序列号：

自增；

整个ID可以理解为：同一毫秒，同一台机器，可以生成2的12次方个不同的ID，所以每毫秒可以生成4096个不重复的ID，如果换算成TPS的话那就是每秒可生成409.6万个不重复的ID

# web

```cmd
 D:\Java-Workspace\nls> npm create vite@5.0.0 web --template vue
Need to install the following packages:
  create-vite@5.0.0
Ok to proceed? (y) y
? Select a framework: » - Use arrow-keys. Return to submit.
>   Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
√ Select a framework: » Vue                              
√ Select a variant: » JavaScript                   
                                                   
Scaffolding project in D:\Java-Workspace\nls\web...

Done. Now run:

  cd web     
  npm install
  npm run dev
  
D:\Java-Workspace\nls\web> npm install --save ant-design-vue@4.1.0

D:\Java-Workspace\nls\web> npm install --save @ant-design/icons-vue

# study版本： 4.2.5
D:\Java-Workspace\nls\web> npm install vue-router

D:\Java-Workspace\nls\web> npm install axios
```

> **npm的package.json，就相当于maven的pom.xml**
>
> package-lock.json：表示锁定小版本
>
> --save：表示下载依赖的同时，更新package.json文件

# 图形验证码

JAVA 图形验证码在CentOS环境无法正常显示

```java
报错信息
jakarta.servlet.ServletException: Handler dispatch failed: java.lang.InternalError: java.lang.reflect.InvocationTargetException
at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1104)
at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903)
at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:205)
at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
at org.springframework.web.filter.AbstractRequestLoggingFilter.doFilterInternal(AbstractRequestLoggingFilter.java:289)
at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
at org.springframework.web.filter.CompositeFilterV i r t u a l F i l t e r C h a i n . d o F i l t e r ( C o m p o s i t e F i l t e r . j a v a : 108 ) a t o r g . s p r i n g f r a m e w o r k . s e c u r i t y . w e b . O b s e r v a t i o n F i l t e r C h a i n D e c o r a t o r VirtualFilterChain.doFilter(CompositeFilter.java:108) at org.springframework.security.web.ObservationFilterChainDecoratorVirtualFilterChain.doFilter(CompositeFilter.java:108)atorg.springframework.security.web.ObservationFilterChainDecoratorFilterObservationS i m p l e F i l t e r O b s e r v a t i o n . l a m b d a SimpleFilterObservation.lambdaSimpleFilterObservation.lambdawrap1 ( O b s e r v a t i o n F i l t e r C h a i n D e c o r a t o r . j a v a : 479 ) a t o r g . s p r i n g f r a m e w o r k . s e c u r i t y . w e b . O b s e r v a t i o n F i l t e r C h a i n D e c o r a t o r . l a m b d a 1(ObservationFilterChainDecorator.java:479) at org.springframework.security.web.ObservationFilterChainDecorator.lambda1(ObservationFilterChainDecorator.java:479)atorg.springframework.security.web.ObservationFilterChainDecorator.lambdawrapUnsecured1 ( O b s e r v a t i o n F i l t e r C h a i n D e c o r a t o r . j a v a : 90 ) a t o r g . s p r i n g f r a m e w o r k . s e c u r i t y . w e b . F i l t e r C h a i n P r o x y . d o F i l t e r I n t e r n a l ( F i l t e r C h a i n P r o x y . j a v a : 219 ) a t o r g . s p r i n g f r a m e w o r k . s e c u r i t y . w e b . F i l t e r C h a i n P r o x y . d o F i l t e r ( F i l t e r C h a i n P r o x y . j a v a : 191 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . C o m p o s i t e F i l t e r 1(ObservationFilterChainDecorator.java:90) at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:219) at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:191) at org.springframework.web.filter.CompositeFilter1(ObservationFilterChainDecorator.java:90)atorg.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:219)atorg.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:191)atorg.springframework.web.filter.CompositeFilterVirtualFilterChain.doFilter(CompositeFilter.java:113)
at org.springframework.web.servlet.handler.HandlerMappingIntrospector.lambda$createCacheFilter3 ( H a n d l e r M a p p i n g I n t r o s p e c t o r . j a v a : 195 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . C o m p o s i t e F i l t e r 3(HandlerMappingIntrospector.java:195) at org.springframework.web.filter.CompositeFilter3(HandlerMappingIntrospector.java:195)atorg.springframework.web.filter.CompositeFilterVirtualFilterChain.doFilter(CompositeFilter.java:113)
at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74)
at org.springframework.security.config.annotation.web.configuration.WebMvcSecurityConfigurationC o m p o s i t e F i l t e r C h a i n P r o x y . d o F i l t e r ( W e b M v c S e c u r i t y C o n f i g u r a t i o n . j a v a : 230 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . D e l e g a t i n g F i l t e r P r o x y . i n v o k e D e l e g a t e ( D e l e g a t i n g F i l t e r P r o x y . j a v a : 352 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . D e l e g a t i n g F i l t e r P r o x y . d o F i l t e r ( D e l e g a t i n g F i l t e r P r o x y . j a v a : 268 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . i n t e r n a l D o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 174 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . d o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 149 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . C o r s F i l t e r . d o F i l t e r I n t e r n a l ( C o r s F i l t e r . j a v a : 91 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . O n c e P e r R e q u e s t F i l t e r . d o F i l t e r ( O n c e P e r R e q u e s t F i l t e r . j a v a : 116 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . i n t e r n a l D o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 174 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . d o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 149 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . R e q u e s t C o n t e x t F i l t e r . d o F i l t e r I n t e r n a l ( R e q u e s t C o n t e x t F i l t e r . j a v a : 100 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . O n c e P e r R e q u e s t F i l t e r . d o F i l t e r ( O n c e P e r R e q u e s t F i l t e r . j a v a : 116 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . i n t e r n a l D o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 174 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . d o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 149 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . F o r m C o n t e n t F i l t e r . d o F i l t e r I n t e r n a l ( F o r m C o n t e n t F i l t e r . j a v a : 93 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . O n c e P e r R e q u e s t F i l t e r . d o F i l t e r ( O n c e P e r R e q u e s t F i l t e r . j a v a : 116 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . i n t e r n a l D o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 174 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . d o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 149 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . S e r v e r H t t p O b s e r v a t i o n F i l t e r . d o F i l t e r I n t e r n a l ( S e r v e r H t t p O b s e r v a t i o n F i l t e r . j a v a : 109 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . O n c e P e r R e q u e s t F i l t e r . d o F i l t e r ( O n c e P e r R e q u e s t F i l t e r . j a v a : 116 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . i n t e r n a l D o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 174 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . d o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 149 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . C h a r a c t e r E n c o d i n g F i l t e r . d o F i l t e r I n t e r n a l ( C h a r a c t e r E n c o d i n g F i l t e r . j a v a : 201 ) a t o r g . s p r i n g f r a m e w o r k . w e b . f i l t e r . O n c e P e r R e q u e s t F i l t e r . d o F i l t e r ( O n c e P e r R e q u e s t F i l t e r . j a v a : 116 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . i n t e r n a l D o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 174 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . A p p l i c a t i o n F i l t e r C h a i n . d o F i l t e r ( A p p l i c a t i o n F i l t e r C h a i n . j a v a : 149 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . S t a n d a r d W r a p p e r V a l v e . i n v o k e ( S t a n d a r d W r a p p e r V a l v e . j a v a : 167 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . S t a n d a r d C o n t e x t V a l v e . i n v o k e ( S t a n d a r d C o n t e x t V a l v e . j a v a : 90 ) a t o r g . a p a c h e . c a t a l i n a . a u t h e n t i c a t o r . A u t h e n t i c a t o r B a s e . i n v o k e ( A u t h e n t i c a t o r B a s e . j a v a : 482 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . S t a n d a r d H o s t V a l v e . i n v o k e ( S t a n d a r d H o s t V a l v e . j a v a : 115 ) a t o r g . a p a c h e . c a t a l i n a . v a l v e s . E r r o r R e p o r t V a l v e . i n v o k e ( E r r o r R e p o r t V a l v e . j a v a : 93 ) a t o r g . a p a c h e . c a t a l i n a . c o r e . S t a n d a r d E n g i n e V a l v e . i n v o k e ( S t a n d a r d E n g i n e V a l v e . j a v a : 74 ) a t o r g . a p a c h e . c a t a l i n a . c o n n e c t o r . C o y o t e A d a p t e r . s e r v i c e ( C o y o t e A d a p t e r . j a v a : 344 ) a t o r g . a p a c h e . c o y o t e . h t t p 11. H t t p 11 P r o c e s s o r . s e r v i c e ( H t t p 11 P r o c e s s o r . j a v a : 391 ) a t o r g . a p a c h e . c o y o t e . A b s t r a c t P r o c e s s o r L i g h t . p r o c e s s ( A b s t r a c t P r o c e s s o r L i g h t . j a v a : 63 ) a t o r g . a p a c h e . c o y o t e . A b s t r a c t P r o t o c o l CompositeFilterChainProxy.doFilter(WebMvcSecurityConfiguration.java:230) at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:352) at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:268) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91) at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:109) at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167) at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90) at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482) at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115) at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93) at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344) at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:391) at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) at org.apache.coyote.AbstractProtocolCompositeFilterChainProxy.doFilter(WebMvcSecurityConfiguration.java:230)atorg.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:352)atorg.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:268)atorg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)atorg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)atorg.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)atorg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)atorg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)atorg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)atorg.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)atorg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)atorg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)atorg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)atorg.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)atorg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)atorg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)atorg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)atorg.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:109)atorg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)atorg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)atorg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)atorg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)atorg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)atorg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)atorg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)atorg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)atorg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)atorg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482)atorg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)atorg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)atorg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)atorg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)atorg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:391)atorg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)atorg.apache.coyote.AbstractProtocolConnectionHandler.process(AbstractProtocol.java:896)
at org.apache.tomcat.util.net.NioEndpointS o c k e t P r o c e s s o r . d o R u n ( N i o E n d p o i n t . j a v a : 1744 ) a t o r g . a p a c h e . t o m c a t . u t i l . n e t . S o c k e t P r o c e s s o r B a s e . r u n ( S o c k e t P r o c e s s o r B a s e . j a v a : 52 ) a t o r g . a p a c h e . t o m c a t . u t i l . t h r e a d s . T h r e a d P o o l E x e c u t o r . r u n W o r k e r ( T h r e a d P o o l E x e c u t o r . j a v a : 1191 ) a t o r g . a p a c h e . t o m c a t . u t i l . t h r e a d s . T h r e a d P o o l E x e c u t o r SocketProcessor.doRun(NioEndpoint.java:1744) at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191) at org.apache.tomcat.util.threads.ThreadPoolExecutorSocketProcessor.doRun(NioEndpoint.java:1744)atorg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)atorg.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)atorg.apache.tomcat.util.threads.ThreadPoolExecutorWorker.run(ThreadPoolExecutor.java:659)
at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
at java.base/java.lang.Thread.run(Thread.java:842)
Caused by: java.lang.InternalError: java.lang.reflect.InvocationTargetException
at java.desktop/sun.font.FontManagerFactory$1.run(FontManagerFactory.java:87)
at java.base/java.security.AccessController.doPrivileged(AccessController.java:318)
at java.desktop/sun.font.FontManagerFactory.getInstance(FontManagerFactory.java:75)
at java.desktop/sun.font.SunFontManager.getInstance(SunFontManager.java:248)
at java.desktop/sun.font.FontDesignMetrics.getMetrics(FontDesignMetrics.java:266)
at java.desktop/sun.java2d.SunGraphics2D.getFontMetrics(SunGraphics2D.java:863)
at cn.hutool.core.img.GraphicsUtil.getCenterY(GraphicsUtil.java:59)
at cn.hutool.core.img.GraphicsUtil.drawString(GraphicsUtil.java:108)
at cn.hutool.core.img.GraphicsUtil.drawStringColourful(GraphicsUtil.java:84)
at cn.hutool.captcha.CircleCaptcha.drawString(CircleCaptcha.java:95)
at cn.hutool.captcha.CircleCaptcha.createImage(CircleCaptcha.java:78)
at cn.hutool.captcha.AbstractCaptcha.createCode(AbstractCaptcha.java:103)
at cn.hutool.captcha.AbstractCaptcha.getCode(AbstractCaptcha.java:127)
at com.yanque.system.service.impl.AuthServiceImpl.getCaptcha(AuthServiceImpl.java:119)
at com.yanque.system.controller.AuthController.getCaptcha(AuthController.java:43)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
```

解决方案

图形验证码依赖了系统组件，只需安装依赖即可解决

Centos 7+系统执行以下两条命令后重启应用服务即可：

```shell
yum install fontconfig
fc-cache --force
```

