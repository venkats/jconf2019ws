package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) {
    //take and takeWhile send a cancel upstream and a complete
    //signal downstream

    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .takeWhile(i -> i < 5)
      .subscribe(System.out::println, System.out::println, () -> System.out.println("DONE"));
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(count++ < 10) {
      if(emitter.isCancelled()) {
        System.out.println("Hey look it was cancelled...");
        break;
      }
      System.out.println("Emitting... " + count);
      emitter.onNext(count);
    }
  }
}