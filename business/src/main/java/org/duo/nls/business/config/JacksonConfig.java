package org.duo.nls.business.config;// package com.jiawa.nls.business.config;

 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.fasterxml.jackson.databind.module.SimpleModule;
 import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

 /**
  * 统一注解，解决前后端交互Long类型精度丢失的问题
  * 在前端JavaScript中处理Long类型数据时，由于JavaScript的Number类型默认被转换为双精度浮点数，其精度有限，只能精确表示2532^{53}2
  * 53以内的整数。对于超过这个范围的长整数，JavaScript会发生精度丢失，导致值变得不准确。因此，当后端返回Long类型数据时，为了避免精度丢失，通常需要将Long类型数据转换为字符串类型进行处理。
  * 在JavaScript中，可以通过以下几种方法处理Long类型数据：
  * 1、直接转换为字符串‌：可以直接调用对象的toString()方法将Long类型数据转换为字符串。这种方法简单直接，但需要确保在前端处理时能够正确解析字符串形式的数字。
  * 2、使用注解进行转换‌：在后端，可以使用Jackson库的注解功能，如@JsonSerialize(using=ToStringSerializer.class)，将Long类型的字段在序列化为JSON时转换为String类型。这样，前端接收到的数据就是字符串形式，避免了精度丢失的问题。
  * 3、使用Jackson2ObjectMapperBuilder；（缺点是后端返给前端的所有的Long类型都会转换成String）
  */
 @Configuration
 public class JacksonConfig {
     @Bean
     public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
         ObjectMapper objectMapper = builder.createXmlMapper(false).build();
         SimpleModule simpleModule = new SimpleModule();
         simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
         objectMapper.registerModule(simpleModule);
         return objectMapper;
     }
 }
