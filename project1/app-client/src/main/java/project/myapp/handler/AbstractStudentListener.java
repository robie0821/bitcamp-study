package project.myapp.handler;

import java.util.List;
import project.myapp.vo.Student;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public abstract class AbstractStudentListener implements ActionListener {

  protected List<Student> list;

  public AbstractStudentListener(List<Student> list) {
    this.list = list;
  }

  protected Student findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Student std = this.list.get(i);
      if (std.getNo() == no) {
        return std;
      }
    }
    return null;
  }

  protected String inputScore(BreadcrumbPrompt prompt, String title) {
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
