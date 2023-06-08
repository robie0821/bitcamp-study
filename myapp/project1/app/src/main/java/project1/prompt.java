package project1;

import java.util.Scanner;

public class prompt {

  public static Scanner sc = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return sc.nextLine();
  }

  public static int inputInt(String title) {
    try {
      return Integer.parseInt(inputString(title));
    } catch (Exception e) {
      System.out.println("유효하지 않은 입력입니다");
      return inputInt(title);
    }
  }
  
  public static void close() {
    sc.close();
  }
}
