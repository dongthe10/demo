package com.hollly.example.mergeRequest;

import java.util.Objects;

/**
 * 请求结果
 *
 * @author hollly
 * @date 2022/5/30 0:32
 */
public class Result {

    private Integer code;

    private String msg;

    private Boolean success;


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Boolean isSuccess() {
        return Objects.equals(code, 0);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                '}';
    }
}
