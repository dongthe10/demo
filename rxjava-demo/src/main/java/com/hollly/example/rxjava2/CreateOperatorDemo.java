package com.hollly.example.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author hollly
 * @date 2021/10/3 0:13
 */
public class CreateOperatorDemo {

    public static void main(String[] args) {
//        test0();
//        test1();
        test2();;

    }


    public static void test0() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("执行第一次");
                emitter.onNext("执行第二次");
                emitter.onNext("执行第三次");
//                emitter.onComplete();
                emitter.onError(new Exception());
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("订阅成功");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("observer收到：" + o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("收到异常信息");
                try {
                    throw e;
                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
                }
            }

            @Override
            public void onComplete() {
                System.out.println("完成处理");
            }
        });
    }

    public static void test1() {
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("执行第一次");
                emitter.onNext("执行第二次");
                emitter.onNext("执行第三次");
//                emitter.onComplete();
                emitter.onError(new Exception());
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("接收到消息");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable o) throws Exception {
                System.out.println("异常了");
            }
        });
        System.out.println(subscribe.isDisposed());
    }

    private static Consumer DEFAULT_OBSERVER = System.out::println;

    public static void test2() {
        Observable.just("22", "33", "44").subscribe(DEFAULT_OBSERVER);
        Observable.fromArray("22", "33", "44", "无线个").subscribe(DEFAULT_OBSERVER);

        ArrayList<Object> list = new ArrayList<>();
        list.add(648);
        Observable.fromIterable(list).subscribe(DEFAULT_OBSERVER);


        Observable.fromFuture(new Future<Object>() {


            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        }).subscribe(DEFAULT_OBSERVER);

        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "null";
            }
        }).subscribe(DEFAULT_OBSERVER);
    }

    public static void test3() {
//        Observable.fromIterable()
    }
}
