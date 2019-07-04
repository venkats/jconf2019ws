package com.agiledeveloper;

import drone.DroneLocator;
import io.reactivex.disposables.Disposable;

import java.util.Scanner;

public class Sample {
  public static void main(String[] args) {
    Disposable disposable = DroneLocator.fetch("DR01")
      //.doOnEach(System.out::println)
      .skipWhile(location -> location.getAltitude() < 50)
      .take(25)
      .subscribe(System.out::println, System.out::println, () -> System.out.println("Drone has landed"));

    new Scanner(System.in).nextLine();
    disposable.dispose();
  }
}