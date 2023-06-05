package bitcamp.util;

import java.util.Scanner;

public class Prompt {

  public static Scanner sc = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.printf(title);
    return sc.next();
  }

  public static void close() {
    sc.close();
  }
}
