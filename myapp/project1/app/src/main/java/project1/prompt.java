package project1;

import java.util.Scanner;

public class prompt {

  public static Scanner sc = new Scanner(System.in);

  public static int inputInteger(String str) {
    System.out.print(str);
    return sc.nextInt();
  }

  public static String inputString(String str) {
    System.out.print(str);
    return sc.next();
  }
  
  public static void close() {
    sc.close();
  }
}
