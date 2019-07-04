package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.schedulers.Schedulers;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(
        emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .observeOn(Schedulers.computation())
      .subscribe(data -> process(data));

    sleep(10000);
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 10) {
      System.out.println("emitting... " + count);
      emitter.onNext(count);
      sleep(500);
    }
  }

  public static void process(int data) {
    System.out.println("received " + data);
    sleep(1000);
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