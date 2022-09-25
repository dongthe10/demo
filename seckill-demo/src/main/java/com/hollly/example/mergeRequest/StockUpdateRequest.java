package com.hollly.example.mergeRequest;

/**
 * 库存扣减请求
 *
 * @author hollly
 * @date 2022/5/29 23:49
 */
public class StockUpdateRequest {


    private Long userId;

    private Long orderId;

    private Integer count;

    public StockUpdateRequest(Long userId, Long orderId, Integer count) {
        this.userId = userId;
        this.orderId = orderId;
        this.count = count;
    }


    public StockUpdateRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
