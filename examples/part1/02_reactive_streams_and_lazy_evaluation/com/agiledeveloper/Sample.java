package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .map(data -> transform(data))
      .subscribe(data -> System.out.println(data), err -> {}, () -> System.out.println("no more data")) //comment out this line and try
      ;

    System.out.println("DONE");
  }

  public static int transform(int data) {
    System.out.println("transform called for " + data);
    return data * 1;
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    System.out.println("starting to emit....");
    int count = 0;

    while(count < 10) {
      emitter.onNext(count++); //this is the mouth of the data channel
    }

    emitter.onComplete(); //this is the mouth of the complete channel.
    //now the data channel is closed

    emitter.onNext(77); //don't do this, the data channel is closed
  }
}