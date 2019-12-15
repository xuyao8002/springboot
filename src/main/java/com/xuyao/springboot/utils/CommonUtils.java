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
import java.net.InetAddress;
import java.net.UnknownHostException;

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


    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        return ipAddress;
    }

}
