package com.hollly.example.rxjava2;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 *
 * Rx2 转换操作符
 *
 * @author hollly
 * @date 2021/10/3 1:19
 */
public class TransOperatorDemo {

    private static Consumer DEFAULT_OBSERVER = System.out::println;

    public static void main(String[] args) {
        Observable.just("aaa")
                .map(a -> "bbb")
                .subscribe(DEFAULT_OBSERVER);

        Observable.just("aaa", "bbb")
                .flatMap(a -> Observable.just("bbb"))
                .subscribe(DEFAULT_OBSERVER);


        // TODO
//        Observable.concat(Observable.just(111)).
    }


}
