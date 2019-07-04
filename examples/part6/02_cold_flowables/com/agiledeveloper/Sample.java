package com.agiledeveloper;

import io.reactivex.Flowable;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void print(String msg, long data) {
    System.out.println(msg + ":" + data);
  }
  public static void main(String[] args) throws InterruptedException {
    Flowable<Long> flowable = Flowable.interval(0, 1, TimeUnit.SECONDS);

    //by default the flowables are cold

    flowable.subscribe(data -> print("S1", data));

    Thread.sleep(5000);

    flowable.subscribe(data -> print("S2", data));

    Thread.sleep(10000);
  }
}