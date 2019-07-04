package com.agiledeveloper;

import drone.DroneLocator;
import io.reactivex.disposables.Disposable;

import java.util.Scanner;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Disposable disposable = DroneLocator.fetch("DR01")
        .subscribe(
            System.out::println,
            System.out::println,
            () -> System.out.println("Drone has landed"));

    Scanner scanner = new Scanner(System.in);
    while(!scanner.nextLine().equals("end")) {
    }

    disposable.dispose();
  }
}