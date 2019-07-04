package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.schedulers.Schedulers;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .filter(data -> check("1", data))
      .observeOn(Schedulers.computation())
      .filter(data -> check("2", data))
      .subscribe(data -> print(data));

    //observeOn will run downstream in a different thread than upstream.

    System.out.println("DONE");

    Thread.sleep(10000);
  }

  private static boolean check(String msg, Integer data) {
    System.out.println("check " + msg + " " + Thread.currentThread());
    return true;
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