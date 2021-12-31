package com.example.controller;

import com.example.config.CustomConfig;
import com.example.config.CommonConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hollly
 * @date 2021/5/3 0:45
 */
@Api(value = "nocas测试配置")
@RestController
@RequestMapping("/nacos")
public class NacosTestController {

    @Autowired
    private CustomConfig customConfig;

    @Autowired
    private CommonConfig commonConfig;

    @GetMapping("/get")
    @ApiOperation("获取配置")
    public Object deal() {

        return customConfig.getAge() + "   " + customConfig.getUserName();
    }

    @GetMapping("/common")
    public Object common() {

        return commonConfig.getVersion();
    }
}

