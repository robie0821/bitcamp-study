package project.app.handler;

import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentAddListener implements StudentActionListener {

  StudentDao studentDao;

  public StudentAddListener(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Student std = new Student();
    std.setName(prompt.inputString("이름? "));
    std.setEmail(prompt.inputString("이메일? "));
    std.setPassword(prompt.inputString("비밀번호? "));

    studentDao.insert(std);
  }
}