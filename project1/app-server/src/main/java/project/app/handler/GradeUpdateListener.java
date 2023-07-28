package project.app.handler;

import java.util.List;
import project.app.vo.Score;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class GradeUpdateListener extends AbstractStudentListener {

  public GradeUpdateListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Score s = this.findBy(prompt.inputInt("학번? "));
    if (std == null) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }

    std.setCpp(inputScore(prompt, "C++(" + std.getCpp() + ")? "));
    std.setJava(inputScore(prompt, "Java(" + std.getJava() + ")? "));
    std.setPython(inputScore(prompt, "Python(" + std.getPython() + ")? "));
    std.setLinux(inputScore(prompt, "Linux(" + std.getLinux() + ")? "));
    std.compute();
  }
}
