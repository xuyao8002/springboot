package com.xuyao.springboot.config;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 非json格式请求参数转换
 */
public class LocalDateTimeConvert implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse(source));
    }
}