package com.hollly.example;

import com.lmax.disruptor.EventFactory;

/**
 * @author hollly
 * @date 2023/10/1 15:12
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
