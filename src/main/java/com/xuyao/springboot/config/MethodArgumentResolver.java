package com.xuyao.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuyao.springboot.annotation.ArgResolver;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

@Component
public class MethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasMethodAnnotation(ArgResolver.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        final Class<?> parameterType = methodParameter.getParameterType();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        //处理json请求数据
        final BufferedReader reader = request.getReader();
        final String json = reader.readLine();
        Object param;
        if (StringUtils.isNoneBlank(json)) {
            final ObjectMapper objectMapper = new ObjectMapper();
            param = objectMapper.readValue(json, parameterType);
        }else{
            param = parameterType.newInstance();
        }
        //处理其它
        final Map<String, String[]> parameterMap = nativeWebRequest.getParameterMap();
        if(MapUtils.isNotEmpty(parameterMap)){
            final Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
            if (CollectionUtils.isNotEmpty(entries)) {
                for (Map.Entry<String, String[]> entry : entries) {
                    final String key = entry.getKey();
                    final String[] value = entry.getValue();
                    final Field field = parameterType.getDeclaredField(key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, param, value[0]);
                }
            }
        }
        return param;
    }
}
