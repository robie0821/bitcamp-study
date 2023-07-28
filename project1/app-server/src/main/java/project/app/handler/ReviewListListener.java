package project.app.handler;

import java.util.List;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class ReviewListListener extends AbstractStudentListener {

  public ReviewListListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.printf("1. C++\n2. Java\n3. Python\n4. Linux\n");

    switch(prompt.inputInt("과목? ")) {
      case 1:
        if (list.isEmpty()) {
          System.out.println("강의평가가 없습니다.");
        } else {
          for (Student std:list) {
            if (std.getCppReview() != null && !std.getCppReview().equals("null")) {
              System.out.printf("*** : %s\n", std.getCppReview());
            }
          }
        }
        break;

      case 2:
        if (list.isEmpty()) {
          System.out.println("강의평가가 없습니다.");
        } else {
          for (Student std:list) {
            if (std.getJavaReview() != null && !std.getJavaReview().equals("null")) {
              System.out.printf("*** : %s\n", std.getJavaReview());
            }
          }
        }
        break;

      case 3:
        if (list.isEmpty()) {
          System.out.println("강의평가가 없습니다.");
        } else {
          for (Student std:list) {
            if (std.getPythonReview() != null && !std.getPythonReview().equals("null")) {
              System.out.printf("*** : %s\n", std.getPythonReview());
            }
          }
        }
        break;

      case 4:
        if (list.isEmpty()) {
          System.out.println("강의평가가 없습니다.");
        } else {
          for (Student std:list) {
            if (std.getLinuxReview() != null && !std.getLinuxReview().equals("null")) {
              System.out.printf("*** : %s\n", std.getLinuxReview());
            }
          }
        }
        break;

      default :
        System.out.println("잘못된 입력입니다.");
    }
  }
}











