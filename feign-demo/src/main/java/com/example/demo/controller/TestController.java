package com.example.demo.controller;

import com.example.demo.service.AsyncService;
import com.example.demo.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hollly
 * @date 2020/12/19 23:32
 */
@RestController
@RequestMapping("/request")
public class TestController {


    @Autowired
    private FeignService feignService;

    @Autowired
    private AsyncService asyncService;


    @GetMapping("/call")
    public Object callFeign() {
        return feignService.getConfig();
    }


    @GetMapping("/print")
    public Object callPrint() {
        asyncService.print();
        return "success";
    }


}
