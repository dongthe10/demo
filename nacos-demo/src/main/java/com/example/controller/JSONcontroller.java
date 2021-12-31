package com.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hollly
 * @date 2021/4/10 14:51
 */
@RestController
@RequestMapping("/json")
public class JSONcontroller {

    @GetMapping("/test")
    public Object deal() {
        Self self = new Self();
        self.code = 1;
        self.desc = "fff";

        Self self2 = new Self();
        self2.code = 2;
        self2.desc = "ggg";
        self.setAbj(self2);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(self);
            Result result = new Result();
            result.code = "fff";
            result.obj = s;
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Self {
        int code;
        String desc;
        Object abj;

        public Object getAbj() {
            return abj;
        }

        public void setAbj(Object abj) {
            this.abj = abj;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    private static class Result {
        String code;

        Object obj;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }
    }

}
