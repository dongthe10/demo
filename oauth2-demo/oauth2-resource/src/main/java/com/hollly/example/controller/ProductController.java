package com.hollly.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hollly
 * @date 2022/8/21 14:31
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping
    public String findAll() {
        return "查询产品列表成功！";
    }
}