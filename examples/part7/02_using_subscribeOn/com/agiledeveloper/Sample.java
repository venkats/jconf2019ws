package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.schedulers.Schedulers;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .subscribeOn(Schedulers.computation())
      .subscribe(data -> print(data));

    //The emit and the receive are in the same thread,
    //but not in the main thread.

    System.out.println("DONE");

    Thread.sleep(10000);
  }

  private static void print(Integer data) {
    System.out.println("received " + data + " in " + Thread.currentThread());
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(count++ < 10) {
      System.out.println("emitting from " + Thread.currentThread());
      emitter.onNext(count);
    }
  }
}