package com.xuyao.springboot.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

import java.time.format.DateTimeFormatter;

@JsonComponent
public class DateFormatConfig {

    @Value("${spring.jackson.date-format}")
    private String pattern;

//    @Value("${spring.jackson.time-zone}")
//    private String timeZone;

//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer formatDate() {
//        return builder -> {
//            DateFormat df = new SimpleDateFormat(pattern);
//            df.setTimeZone(TimeZone.getTimeZone(timeZone));
//            builder.failOnEmptyBeans(false)
//                    .failOnUnknownProperties(false)
//                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                    .dateFormat(df);
//        };
//    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer formatLocalDateTime() {
        return builder -> {
            builder.simpleDateFormat(pattern);
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern)));
        };
    }
}