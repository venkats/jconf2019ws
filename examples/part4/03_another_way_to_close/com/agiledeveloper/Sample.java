package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Disposable disposable =
      Flowable.interval(0, 1, TimeUnit.SECONDS)
        .subscribe(System.out::println);

    Thread.sleep(5000);

    System.out.println("let's cancel...");
    disposable.dispose();

    Thread.sleep(2000);
  }
}