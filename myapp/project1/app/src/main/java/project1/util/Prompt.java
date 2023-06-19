package project1.util;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {

  private Scanner sc;

  public Prompt() {
    this.sc = new Scanner(System.in);
  }

  public Prompt(InputStream in) {
    this.sc = new Scanner(in);
  }

  public String inputString(String title, Object... args) {
    System.out.printf(title, args);
    return this.sc.nextLine();
  }

  public int inputInt(String title, Object... args) {
    try {
      return Integer.parseInt(this.inputString(title, args));
    } catch (Exception e) {
      System.out.println("유효하지 않은 입력입니다");
      return inputInt(title, args);
    }
  }

  public void close() {
    this.sc.close();
  }
}
