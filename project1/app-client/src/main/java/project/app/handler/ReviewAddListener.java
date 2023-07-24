package project.app.handler;

import java.util.List;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class ReviewAddListener extends AbstractStudentListener {

  public ReviewAddListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = this.findBy(prompt.inputInt("학번? "));
    if (std == null) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }
    std.setCppReview(prompt.inputString("C++ 강의평가? "));
    std.setJavaReview(prompt.inputString("Java 강의평가? "));
    std.setPythonReview(prompt.inputString("Python 강의평가? "));
    std.setLinuxReview(prompt.inputString("Linux 강의평가? "));
  }
}











