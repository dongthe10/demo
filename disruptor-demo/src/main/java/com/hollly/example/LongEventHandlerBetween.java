package com.hollly.example;

import com.lmax.disruptor.EventHandler;

/**
 * @author hollly
 * @date 2023/10/1 15:12
 */
public class LongEventHandlerBetween implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        //CommonUtils.accumulation();
        System.out.println("LongEventHandlerBetween consumer:" + Thread.currentThread().getName() + " Event: value=" + event.getValue() + ",sequence=" + sequence);
    }
}
