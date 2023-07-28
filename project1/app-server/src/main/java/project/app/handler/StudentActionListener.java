package project.app.handler;

import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public interface StudentActionListener extends ActionListener {

  static String inputScore(BreadcrumbPrompt prompt, String title) {
    while(true) {
      String temp = prompt.inputString(title);
      switch (temp) {
        case "A+","A","B+","B","C+","C","D+","D","F":
          return temp;
        case "a+","a","b+","b","c+","c","d+","d","f":
          return temp.toUpperCase();
        default:
          System.out.println("유효하지 않은 입력입니다.");
      }
    }
  }
}