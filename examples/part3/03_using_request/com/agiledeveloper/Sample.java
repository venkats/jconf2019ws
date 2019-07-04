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
      .map(data -> data * 1)
      .subscribe(new Subscriber<Integer>() {
        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
          this.subscription = subscription;

          this.subscription.request(5);
        }

        @Override
        public void onNext(Integer data) {
          System.out.println("received: " + data);

          if(data < 3) this.subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {
          System.out.println("no more data");
        }
      });

    System.out.println("done");
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    System.out.println("starting to emit");

    int count = 0;

    while(count < 10) {
      count++;
      System.out.println("emitting " + count);
      emitter.onNext(count);
    }

    emitter.onComplete();
  }
}