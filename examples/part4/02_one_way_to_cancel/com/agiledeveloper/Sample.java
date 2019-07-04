package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.*;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
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
          this.subscription.request(1);

          if(data == 5) {
            System.out.println("found the data we want, no more data please...");
            this.subscription.cancel();
          }
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
      });
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(count < 10 && !emitter.isCancelled()) {
      System.out.println("Emitting... " + ++count);
      emitter.onNext(count);
    }
  }
}