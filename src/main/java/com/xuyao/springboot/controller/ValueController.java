package com.xuyao.springboot.controller;

import com.xuyao.springboot.config.ValueListConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/value")
public class ValueController {

    @Autowired
    private ValueListConfig valueListConfig;

    @Value("${value1.array0}")
    private int[] array0;

    @Value("#{'${value2.list1:}'.empty ? null : '${value2.list1:}'.split(',')}")
    private List<String> splitList;

    @Value("${set:1,2,3}")
    private Set<String> set;

    @Value("#{${value3.map}}")
    private Map<String, Object> map;

    @RequestMapping("/list")
    public Object list(){
        return valueListConfig;
    }

    @RequestMapping("/array")
    public Object array(){
        return array0;
    }

    @RequestMapping("/splitList")
    public Object splitList(){
        return splitList;
    }

    @RequestMapping("/set")
    public Object set(){
        return set;
    }

    @RequestMapping("/map")
    public Object map(){
        return map;
    }
}
