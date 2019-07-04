package com.agiledeveloper;

import io.reactivex.Flowable;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Flowable.interval(0, 1, TimeUnit.SECONDS)
      .map(i -> i % 10)
      .skipWhile(i -> i < 5)
      .subscribe(System.out::println);

    Thread.sleep(30000);

    //filter will open or close for each value that shows up
    //skip and skipWhile are closed in the beginning, but once they
    //open, they stay open forever.
  }
}