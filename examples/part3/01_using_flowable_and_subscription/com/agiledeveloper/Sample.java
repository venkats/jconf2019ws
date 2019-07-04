package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import java.util.*;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .filter(data -> data % 2 == 0)
      .map(data -> data * 1)
      .subscribe(System.out::println, System.out::println, () -> System.out.println("completed"));
    //subsribe(data channel, error channel, complete channel)

    System.out.println("done");
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    System.out.println("starting to emit");

    int count = 0;

    while(count < 10) {
      emitter.onNext(count++);
    }

    emitter.onComplete();
  }
}