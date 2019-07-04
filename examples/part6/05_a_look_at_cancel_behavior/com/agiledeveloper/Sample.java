package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Sample {
  public static void print(String msg, long data) {
    System.out.println(msg + ":" + data);
  }
  public static void main(String[] args) throws InterruptedException {
    Flowable<Long> flowable = //Flowable.interval(0, 1, TimeUnit.SECONDS);
        Flowable.<Long>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
        .subscribeOn(Schedulers.computation())
        .share()
        ;

    //share turns that into a hot flowable.

    Disposable disposable1 = flowable.subscribe(data -> print("S1", data));

    Thread.sleep(5000);

    Disposable disposable2 = flowable.subscribe(data -> print("S2", data));

    Thread.sleep(10000);

    disposable1.dispose();

    Thread.sleep(5000);

    disposable2.dispose();

    Thread.sleep(10000);
  }

  private static void emit(FlowableEmitter<Long> emitter) {
    System.out.println("Got a new subscription");

    long count = 0;
    while(count++ < 30 && !emitter.isCancelled()) {
      System.out.println("emitting...");
      emitter.onNext(count);
      sleep(1000);
    }
  }

  private static boolean sleep(int ms) {
    try {
      Thread.sleep(ms);
      return true;
    } catch (InterruptedException e) {
      return false;
    }
  }
}