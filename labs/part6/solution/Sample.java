package com.agiledeveloper;

import drone.DroneLocation;
import drone.DroneLocator;
import io.reactivex.Flowable;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Flowable<DroneLocation> dr01 = DroneLocator.fetch("DR01");

    dr01.subscribe(System.out::println);
    dr01.subscribe(System.out::println);

    Thread.sleep(10000);

    //it is hot because the output of the two subscriptions are
    //exactly the same.

    //or we can use a well proven technique called cheeting.
    //Take a look at the code by clicking on control+B or whatever
    //shortcut for the IDE to reveal the code.

    //We see call to share() in there.
  }
}