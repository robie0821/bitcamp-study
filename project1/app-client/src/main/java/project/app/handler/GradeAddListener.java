package project.app.handler;

import java.util.List;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class GradeAddListener extends AbstractStudentListener {

  public GradeAddListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = this.findBy(prompt.inputInt("학번? "));
    if (std == null) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }
    std.setCpp(inputScore(prompt, "C++? "));
    std.setJava(inputScore(prompt, "Java? "));
    std.setPython(inputScore(prompt, "Python? "));
    std.setLinux(inputScore(prompt, "Linux? "));
    std.compute();
  }
}
