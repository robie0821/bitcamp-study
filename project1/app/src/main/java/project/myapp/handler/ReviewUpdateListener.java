package project.myapp.handler;

import java.util.List;
import project.myapp.vo.Student;
import project.util.BreadcrumbPrompt;

public class ReviewUpdateListener extends AbstractStudentListener {

  public ReviewUpdateListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = this.findBy(prompt.inputInt("번호? "));
    System.out.printf("1. C++\n2. Java\n3. Python\n4.Linux\n");

    String str = null;

    switch(prompt.inputInt("과목? ")) {
      case 1:
        str = prompt.inputString("(%s)\nC++ 강의평가? ", std.getCppReview());
        if (str != null) {
          std.setCppReview(str);
        }
        str = null;
        break;

      case 2:
        str = prompt.inputString("(%s)\nJava 강의평가? ", std.getJavaReview());
        if (str != null) {
          std.setJavaReview(str);
        }
        str = null;
        break;

      case 3:
        str = prompt.inputString("(%s)\nPython 강의평가? ", std.getPythonReview());
        if (str != null) {
          std.setPythonReview(str);
        }
        str = null;
        break;

      case 4:
        str = prompt.inputString("(%s)\nLinux 강의평가? ", std.getLinuxReview());
        if (str != null) {
          std.setLinuxReview(str);
        }
        str = null;
        break;

      default :
        System.out.println("잘못된 입력입니다.");
    }
  }
}











