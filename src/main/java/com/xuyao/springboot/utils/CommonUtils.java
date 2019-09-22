package com.xuyao.springboot.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuyao.springboot.consts.Consts;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommonUtils {


    public static String hashAlgorithmName = "MD5";

    public static int hashIterations = 6;

    private CommonUtils() {
    }

    public static HttpServletResponse jsonResponse(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        return response;
    }

    public static String toJSON(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public static void printJSON(Object obj, HttpServletResponse response) throws IOException {
        HttpServletResponse jsonResponse = jsonResponse(response);
        PrintWriter writer = jsonResponse.getWriter();
        writer.println(toJSON(obj));
        writer.close();
    }

    public static String getTokenId(HttpServletRequest request){
        String tokenId = request.getHeader(Consts.TOKEN_ID);
        if(StringUtils.isBlank(tokenId)){
            tokenId = request.getParameter(Consts.TOKEN_ID);
        }
        return tokenId;
    }

    public static String encryPassowrd(String username, String password) {
        return new SimpleHash(CommonUtils.hashAlgorithmName, password, CommonUtils.getSalt(username), CommonUtils.hashIterations).toString();
    }

    public static ByteSource getSalt(String username) {
        return ByteSource.Util.bytes(username);
    }

    public static String getHashVal(String hashAlgorithmName, Object credentials, Object salt, int hashIterations){
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toHex();
    }

    public static void toDTO(Object source, Object target){
        BeanUtils.copyProperties(source, target);
    }

    public static <T> T toDTO(Object source, Class<T> targetClass){
        T targetInstance = null;
        try {
            targetInstance = targetClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, targetInstance);
        return targetInstance;
    }
}
