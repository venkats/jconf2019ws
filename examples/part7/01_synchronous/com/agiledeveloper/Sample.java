package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .subscribe(data -> print(data));

    System.out.println("DONE");
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