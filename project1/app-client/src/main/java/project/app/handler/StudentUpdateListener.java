package project.app.handler;

import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentUpdateListener implements StudentActionListener {

  StudentDao studentDao;

  public StudentUpdateListener(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int studentNo = prompt.inputInt("학번? ");

    Student std = studentDao.findBy(studentNo);
    if (std == null) {
      System.out.println("해당 학번의 학생이 존재하지 않습니다.");
    }

    std.setName(prompt.inputString("이름(%s)? ", std.getName()));
    std.setEmail(prompt.inputString("이메일(%s)? ", std.getEmail()));
  }
}











