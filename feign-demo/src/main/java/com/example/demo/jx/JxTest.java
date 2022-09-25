package com.example.demo.jx;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author hollly
 * @date 2021/10/2 17:29
 */
public class JxTest {

    public static void main(String[] args) {
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                //执行一些其他操作
//                //.............
//                //执行完毕，触发回调，通知观察者
//                e.onNext("我来发射数据");
//            }
//        });
//
//
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            //观察者接收到通知,进行相关操作
//            public void onNext(String aLong) {
//                System.out.println("我接收到数据了");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//
//        observable.subscribe(observer);

//        new ArrayList<>().stream().filter(x -> true).collect(Collectors.toList());

        String a = "ffff";
        a.substring(0, 10);
        a.length();
        int[] ints = new int[]{1, 1, 11};
        int length = ints.length;
        char[] array = a.toCharArray();


        ArrayList<Integer> objects = new ArrayList<>();
        objects.stream().sorted((x1, x2) -> x2 - x1).collect(Collectors.toList());


        char c1 = 2323;

    }
}
