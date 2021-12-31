package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author hollly
 * @date 2021/5/4 0:06
 */
@FeignClient("nacos-service")
public interface FeignService {

    @RequestMapping(value = "/nacos/get", method = GET)
    String getConfig();
}
