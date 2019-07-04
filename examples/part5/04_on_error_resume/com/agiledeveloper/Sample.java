package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static Flowable<Integer> create(int attemptsLeft) {
    Flowable<Integer> flowable = Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER);
    if(attemptsLeft == 0)
      return flowable;
    else
      return flowable.onErrorResumeNext(
          (Throwable throwable) -> create(attemptsLeft - 1));
  }

  public static void main(String[] args) {
    create(5)
      .map(data -> data * 1)
      .subscribe(System.out::println, err -> System.out.println("ERROR: " + err));
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    System.out.println("starting an emitter....");
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