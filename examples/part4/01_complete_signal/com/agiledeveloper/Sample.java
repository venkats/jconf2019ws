package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .subscribe(System.out::println, System.out::println, () -> System.out.println("DONE... clean up"));
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    emitter.onNext(1);
    emitter.onNext(2);

    emitter.onComplete();

    emitter.onNext(3); //no op, ignored, data channel has been closed already
  }
}