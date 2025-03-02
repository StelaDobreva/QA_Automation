package com.foobar;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FooBarUtilTest {

  @DataProvider(name = "fooBarValidData")
  public Object[][] provideTestDataValid() {
    return new Object[][] {
      {"3", "foo"},
      {"5", "bar"},
      {"15", "foobar"},
      {"0", "0"},
      {"3,5,15,7", "foo,bar,foobar,7"},
      {"03", "foo"}, // Leading zero
      {"05", "bar"},
      {"015", "foobar"},
      {"-3", "foo"}, // Negative number
      {"99999999", "foo"}, // Large number
      {"2147483647", "2147483647"}, // Max int
      {"-2147483648", "-2147483648"}, // Min int value
    };
  }

  @Test(dataProvider = "fooBarValidData")
  public void testFooBarWithValidInput(String input, String expectedOutput) {
    Assert.assertEquals(FooBarUtil.fooBar(input), expectedOutput);
  }

  @DataProvider(name = "fooBarInvalidData")
  public Object[][] provideTestDataInvalid() {
    return new Object[][] {
      {"a"},
      {"3,a,15,1"},
      {"!"},
      {""}, // Empty string
      {" "}, // Space only
      {" 3"}, // Leading space
      {"5 "}, // Trailing space
      {" 15 "}, // Both leading & trailing space
      {
        ","
      }, // Only comma. Test is failing here because fooBarUtil.java does not handle empty values
      // correctly.
      {
        ",,"
      }, // Only commas. Test is failing here because fooBarUtil.java does not handle empty values
      // correctly.
      {"3,,5"}, // Double commas
      {"3;5;15"}, // Semicolon separator
      {"3|5|15"}, // Pipe separator
      {"3\n5\n15"}, // Newline separator
      {"Ⅲ"}, // Roman numeral 3
      {
        null
      }, // Include null to check if it's handled like other invalid inputs, although it is not a
      // String.
    };
  }

  @Test(dataProvider = "fooBarInvalidData")
  public void testFooBarWithInvalidInput(String input) {
    Assert.assertEquals(FooBarUtil.fooBar(input), "[Error] Invalid input.");
  }

  @DataProvider(name = "fooBarInvalidTypeData")
  public Object[][] provideNonStringTestData() {
    return new Object[][] {
      {1}, // Integer
      {3.14}, // Double
      {true}, // Boolean
      {new Object()}, // Object
    };
  }

  @Test(dataProvider = "fooBarInvalidTypeData", expectedExceptions = ClassCastException.class)
  public void testFooBarWithInvalidType(Object input) {
    FooBarUtil.fooBar((String) input);
  }
}
