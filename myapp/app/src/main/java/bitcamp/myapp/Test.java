package bitcamp.myapp;

import java.util.HashMap;

public class Test {

  public static void main(String[] args) {
    String data = "A 12";
    HashMap<String, Boolean> map = new HashMap<>();

    char key = data.charAt(0);
    int value = Character.getNumericValue(data.charAt(2));

    System.out.println(key);
    System.out.println(value);

    map.containsKey("asdf");
    map.put("asdf", true);

    String.valueOf(123);

  }

}
