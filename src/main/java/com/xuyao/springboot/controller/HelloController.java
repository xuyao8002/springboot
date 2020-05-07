package com.xuyao.springboot.controller;

import com.xuyao.springboot.bean.dto.ValidDTO;
import com.xuyao.springboot.exception.CustomException;
import com.xuyao.springboot.service.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 无分组校验
     * @param validDTO
     * @param result
     * @return
     */
    @RequestMapping("/valid")
    public Object valid(@Validated ValidDTO validDTO, BindingResult result) {
        if (result.hasErrors()) {
            return result.toString();
        }
        return "valid";
    }

    /**
     * Insert分组校验
     * @param validDTO
     * @param result
     * @return
     */
    @RequestMapping("/insertValid")
    public Object insertValid(@Validated(ValidDTO.Insert.class) ValidDTO validDTO, BindingResult result) {
        if (result.hasErrors()) {
            return result.toString();
        }
        return "insertValid";
    }

    /**
     * Update分组校验
     * @param validDTO
     * @param result
     * @return
     */
    @RequestMapping("/updateValid")
    public Object updateValid(@Validated(ValidDTO.Update.class) ValidDTO validDTO, BindingResult result) {
        if (result.hasErrors()) {
            return result.toString();
        }
        return "updateValid";
    }


}