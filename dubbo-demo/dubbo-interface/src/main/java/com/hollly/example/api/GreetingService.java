package com.hollly.example.api;

/**
 * @author hollly
 * @date 2022/6/3 11:49
 */
public interface GreetingService {

    int size = 10;

    /**
     * 打招呼
     * @return
     */
    String speaking();

    /**
     * 握手
     * @return
     */
    boolean handleShake();
}
