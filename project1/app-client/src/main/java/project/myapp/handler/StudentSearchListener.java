package project.myapp.handler;

import java.util.List;
import project.myapp.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentSearchListener extends AbstractStudentListener {

  public StudentSearchListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = this.findBy(prompt.inputInt("학번? "));
    if (std == null) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }

    System.out.printf("비밀번호: %s\n", std.getPassword());
  }
}











