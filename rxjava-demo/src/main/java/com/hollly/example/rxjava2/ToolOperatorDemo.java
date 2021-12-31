package com.hollly.example.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author hollly
 * @date 2021/10/4 0:06
 */
public class ToolOperatorDemo {

    private static Consumer DEFAULT_OBSERVER = System.out::println;

    public static void main(String[] args) throws InterruptedException {



        Observable.just("aaa", "bbb")
                .subscribeOn(Schedulers.io()) // 决定执行subscribe方法执行的线程（观察者执行的线程，多次调用只有第一次生效）
//                .observeOn(Schedulers.computation()) // 决定下游方法执行的线程（观察者执行的线程，指定一次生效一次）
                .map(a -> Observable.just("bbb"))
                .subscribe(DEFAULT_OBSERVER);

        TimeUnit.SECONDS.sleep(9999);
    }
}
