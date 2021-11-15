package com.xuyao.springboot.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam String fileUrl, @RequestParam String name)
            throws IOException {
        URL url = new URL(fileUrl);
        byte[] bytes = IOUtils.toByteArray(url.openStream());
        String fileName = URLEncoder.encode(name, "UTF-8") + "." + fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        respHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
        return new ResponseEntity<>(bytes, respHeaders, HttpStatus.OK);
    }

}