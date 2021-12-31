package com.hollly.example;

import com.alipay.sofa.jraft.Iterator;
import com.alipay.sofa.jraft.core.StateMachineAdapter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 状态机，用于处理业务逻辑
 *
 * 继承adapter，不用实现StateMachine的所有接口
 *
 * @author hollly
 * @date 2021/10/7 17:14
 */
public class TestStateMachine extends StateMachineAdapter {
    private AtomicLong leaderTerm = new AtomicLong(-1);
    @Override
    public void onApply(Iterator iter) {
        System.out.println("onApply");
        while(iter.hasNext()){
            //应用任务到状态机
            System.out.println("状态机接收到消息：" + new String(iter.getData().array()));
            iter.next();
        }
    }

    @Override
    public void onLeaderStart(long term) {
        //保存 leader term
        this.leaderTerm.set(term);
        super.onLeaderStart(term);
    }

}