package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    //solution1();
    solution2();
  }

  public static void solution1() {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .map(data -> data * 2)
      .subscribe(System.out::println);
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(count < 10) {
      emitter.onNext(count++);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void solution2() throws InterruptedException {
    Flowable.interval(0, 1, TimeUnit.SECONDS)
      .map(data -> data * 2)
      .subscribe(System.out::println);

    Thread.sleep(10000);
  }
}