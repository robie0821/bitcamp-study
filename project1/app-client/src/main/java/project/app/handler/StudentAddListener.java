package project.app.handler;

import java.util.List;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentAddListener extends AbstractStudentListener {

  public StudentAddListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = new Student();
    while (true) {
      int no = prompt.inputInt("학번? ");
      if (this.findBy(no) == null) {
        std.setNo(no);
        break;
      }
      System.out.println("이미 등록되어있는 학번입니다.");
    }
    std.setName(prompt.inputString("이름? "));
    std.setPassword(prompt.inputString("비밀번호? "));
    this.list.add(std);
  }
}











