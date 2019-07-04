package com.agiledeveloper;

import io.reactivex.Flowable;

import java.util.*;
import java.util.concurrent.TimeUnit;

class StockPrice {
  public static int getPrice(final String ticker) {
    //This simply create some fake prices
    return new Random().nextInt(2000);
  }
}

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    final List<String> symbols = List.of(
        "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
        "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");


    Flowable.interval(0, 1, TimeUnit.SECONDS)
      .map(index -> index.intValue() % symbols.size())
      .map(index -> symbols.get(index))
      .map(symbol -> symbol + " : " + StockPrice.getPrice(symbol))
      .subscribe(System.out::println);

    Thread.sleep(30000);
  }
}
