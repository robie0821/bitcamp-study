package bitcamp.myapp;

import java.util.HashMap;

public class Test {

  public static void main(String[] args) {
    String data = "A 12";
    HashMap<String, Integer> map = new HashMap<>();

    char key = data.charAt(0);
    int value = Integer.parseInt(data.substring(2));

    System.out.println(key);
    System.out.println(value);

  }

}
