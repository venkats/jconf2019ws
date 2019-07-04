package com.agiledeveloper;

import drone.DroneLocator;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    DroneLocator.fetch("DR10")
      .onErrorResumeNext(throwable -> {
        System.out.println("ERROR: " + throwable);
        return DroneLocator.fetch("DR01");
      })
      .subscribe(System.out::println, err -> System.out.println("ERROR: " + err));

    Thread.sleep(20000);
  }
}