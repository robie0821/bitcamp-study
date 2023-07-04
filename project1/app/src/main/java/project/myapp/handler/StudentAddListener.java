package project.myapp.handler;

import java.util.List;
import project.myapp.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentAddListener extends AbstractStudentListener {

  public StudentAddListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = new Student();
    std.setNo(prompt.inputInt("학번? "));
    std.setName(prompt.inputString("이름? "));
    std.setPassword(prompt.inputString("비밀번호? "));
    this.list.add(std);
  }
}











