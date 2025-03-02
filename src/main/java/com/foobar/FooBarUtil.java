package com.foobar;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FooBarUtil {
  public static String fooBar(String input) {
    try {
      Set<Integer> duplicatesContainer = new HashSet<>();
      String fooBarsList =
          Arrays.stream(input.split(","))
              .map(Integer::parseInt)
              .map(
                  number ->
                      checkForFooBar(number) + (duplicatesContainer.add(number) ? "" : "-copy"))
              .collect(Collectors.joining(","));
      System.out.println(fooBarsList);
      return fooBarsList;
    } catch (Exception exception) {
      String errorMessage = "[Error] Invalid input.";
      System.out.println(errorMessage);
      return errorMessage;
    }
  }

  private static String checkForFooBar(int number) {
    if (number == 0 || number % 3 != 0 && number % 5 != 0) return String.valueOf(number);
    return (number % 3 == 0 ? "foo" : "") + (number % 5 == 0 ? "bar" : "");
  }
}
