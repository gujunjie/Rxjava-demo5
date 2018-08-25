package com.example.abc.myapplication25;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {


    public static final String TAG="text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxjavaDo();
    }


    public void RxjavaDo()
    {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {

                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("error :("));

            }
        })//每一次数据的发送都会执行
                .doOnEach(new Consumer<Notification<Integer>>() {
            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {
                Log.d(TAG, "dooneach"+integerNotification.getValue());
            }
        })
               //在onNext事件之前执行
                .doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext"+integer);
            }
        })
               //在onNext事件之后执行
                .doAfterNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doAfterNext"+integer);
            }
        })
              //在发送事件成功时执行
                .doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "doOnComplete");
            }
        })
             //在发送错误事件时执行
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError");
                    }
                })
                //在订阅的时候执行
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG, "doOnSubscribe");
                    }
                })
                //在发送事件完毕时执行，无论成功还是异常
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doAfterTerminate");
                    }
                })

                //最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doFinally");
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext: "+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }
}
