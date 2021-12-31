package com.example.controller;

import com.example.service.AsncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hollly
 * @date 2020/12/19 23:32
 */
@RestController
@RequestMapping("/request")
public class TestController {


    @Autowired
    private AsncService asncService;


    @PostMapping("/map1")
    public Object deal(@RequestParam("date") Date date) {

        return date;
    }

    @GetMapping("/map2")
    public Object deal2(@RequestParam Date date) {
        System.out.println("ffff");
        return date;
    }

    @PostMapping("/map3")
    public Object deal3(LocalDate date) {
        System.out.println("ffff");
        return date;
    }

    @GetMapping("/map33")
    public Object deal33(String date) {
        System.out.println("ffff");
        return date;
    }

    @PostMapping("/map333")
    public Object deal3withPost(@RequestParam(name = "value") String value, LocalDateTime date) {
        System.out.println("ffff");
        return date;
    }

    @GetMapping("/start")
    public Object startMethod() {
        for (int i = 0; i < 4; i++) {
            asncService.print();
        }
        return "success";
    }




}
