package com.agiledeveloper;

import drone.DroneLocation;
import drone.DroneLocator;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    DroneLocator.fetch("DR01")
      .subscribe(new Subscriber<DroneLocation>() {
        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
          this.subscription = subscription;
          subscription.request(10);
        }

        @Override
        public void onNext(DroneLocation droneLocation) {
          System.out.println(droneLocation);

          if(droneLocation.getAltitude() < 50) {
            subscription.request(1);
          }
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {
          System.out.println("The drone has landed");
        }
      });


    Thread.sleep(100000);
  }
}