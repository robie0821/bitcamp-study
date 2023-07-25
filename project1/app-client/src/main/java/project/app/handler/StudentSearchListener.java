package project.app.handler;

import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentSearchListener implements StudentActionListener {

  StudentDao studentDao;

  public StudentSearchListener(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int studentNo = prompt.inputInt("학번? ");

    Student std = studentDao.findBy(studentNo);
    if (std == null) {
      System.out.println("존재하지 않는 이메일주소입니다.");
      return;
    }

    System.out.printf("비밀번호: %s\n", std.getPassword());
  }
}











