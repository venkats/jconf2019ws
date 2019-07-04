package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(
        emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .subscribe(new Subscriber<Integer>() {
        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
          this.subscription = subscription;
          this.subscription.request(1);
        }

        @Override
        public void onNext(Integer data) {
          System.out.println("received " + data);
          sleep(1000);
          subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
      });

    sleep(10000);
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 10) {
      System.out.println("emitting... " + count);
      emitter.onNext(count);
      sleep(500);
    }
  }

  public static void process(int data) {
    System.out.println("received " + data);
    sleep(1000);
  }

  private static boolean sleep(int ms) {
    try {
      Thread.sleep(ms);
      return true;
    } catch (InterruptedException e) {
      return false;
    }
  }
}