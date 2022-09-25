package com.hollly.example.mergeRequest;

/**
 * 异步返回机制
 *
 * @author hollly
 * @date 2022/5/30 0:31
 */
public class RequestPromise {


    private StockUpdateRequest stockUpdateRequest;

    private Result result;

    public RequestPromise(StockUpdateRequest stockUpdateRequest, Result result) {
        this.stockUpdateRequest = stockUpdateRequest;
        this.result = result;
    }

    public RequestPromise(StockUpdateRequest stockUpdateRequest) {
        this.stockUpdateRequest = stockUpdateRequest;
    }

    public StockUpdateRequest getStockUpdateRequest() {
        return stockUpdateRequest;
    }

    public void setStockUpdateRequest(StockUpdateRequest stockUpdateRequest) {
        this.stockUpdateRequest = stockUpdateRequest;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


}
