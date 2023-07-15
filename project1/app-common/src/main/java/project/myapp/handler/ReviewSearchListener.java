package project.myapp.handler;

import java.util.List;
import project.myapp.vo.Student;
import project.util.BreadcrumbPrompt;

public class ReviewSearchListener extends AbstractStudentListener {

  public ReviewSearchListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = this.findBy(prompt.inputInt("학번? "));
    if (std == null) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }
    System.out.println("C++    : " + std.getCppReview());
    System.out.println("Java   : " + std.getJavaReview());
    System.out.println("Python : " + std.getPythonReview());
    System.out.println("Linux  : " + std.getLinuxReview());
  }
}











