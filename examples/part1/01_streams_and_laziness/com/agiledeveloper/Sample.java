package com.agiledeveloper;

import java.util.Arrays;
import java.util.List;

public class Sample {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    numbers.stream()
      .filter(e -> e % 2 == 0)
      .mapToInt(e -> transform(e))
      //.sum() //uncomment this line and see the difference in output
      ;

    System.out.println("DONE");
  }

  private static int transform(Integer input) {
    System.out.println("tranform called for " + input);
    return input * 2;
  }
}

/*
Reactive programming is data flow computing

Reactive programming ==
	functional programming++
	It is an abstraction on top of FP

Java 8 Streams				Reactive Streams
Pipeline							Pipeline
	 of functions					 of functions
Lazy evaluation				Lazy evaluation
----------------------------------------
exceptions?
	good luck						deal with it down stream

data channel only			data channel -------->
                      error channel ------->
											complete channel----->

no forking						multiple subscribers
serial & parallel			synchronous and asynchronous


 */
