package com.agiledeveloper;

import io.reactivex.Flowable;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Flowable.interval(0, 1, TimeUnit.SECONDS)
      .map(i -> i % 10)
      .takeWhile(i -> i < 7)
      .subscribe(System.out::println);

    Thread.sleep(30000);

  }
}