package com.hollly.example.netty.copy;

/**
 * @author hollly
 * @date 2021/12/5 16:31
 */
public enum StatusEnum {

    NEW("fff", 333)
    ;


    private StatusEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    private String name;

    private Integer code;
}
