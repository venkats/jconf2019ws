package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .onErrorResumeNext(throwable -> {
        System.out.println(throwable);
        System.out.println("switching to another service...");
        return Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER);
      })
      .map(data -> data * 1)
      .subscribe(System.out::println, err -> System.out.println("ERROR: " + err));
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(count++ < 10) {
      if(count == 6) {
        emitter.onError(new RuntimeException("something went wrong"));
        //here the data channel was closed. Any emit after this will
        //not have any effect.
        break;
      }
      emitter.onNext(count);
    }
  }
}