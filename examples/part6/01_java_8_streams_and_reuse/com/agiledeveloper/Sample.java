package com.agiledeveloper;

import java.util.*;
import java.util.stream.Stream;

public class Sample {
  public static void main(String[] args) {
   List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

    Stream<Integer> stream = numbers.stream();

    stream.map(e -> e * 2)
      .forEach(System.out::println);

    stream.map(e -> e * 3)  //ERROR at runtime. Can't reuse.
      .forEach(System.out::println);

    //streams as like qtips, do not save and reuse them.
  }
}