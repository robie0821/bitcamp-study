package project.app.handler;

import java.util.List;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentDeleteListener extends AbstractStudentListener {

  public StudentDeleteListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = this.findBy(prompt.inputInt("학번? "));
    if (std == null) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }

    list.remove(std);


  }
}











