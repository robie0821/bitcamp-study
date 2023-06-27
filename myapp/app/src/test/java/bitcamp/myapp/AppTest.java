package bitcamp.myapp;

//import java.util.*;

class AppTest {
  public static void main(String[] args) {
    System.out.println(factorial(15));
  }

  static int factorial(int value) {
    if (value == 1) {
      return 1;
    }
    return value * factorial(value - 1);
  }
}
