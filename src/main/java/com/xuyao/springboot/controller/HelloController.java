package com.xuyao.springboot.controller;

import com.xuyao.springboot.exception.CustomException;
import com.xuyao.springboot.service.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private ICustomService customService;

    @GetMapping("/npe")
    public String npe(String name) throws CustomException {
        return customService.hello(name);
    }

    @GetMapping("/stopWatch")
    public void stopWatch() throws InterruptedException {
        customService.stopWatch();
    }
}