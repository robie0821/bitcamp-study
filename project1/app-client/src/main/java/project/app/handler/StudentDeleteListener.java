package project.app.handler;

import project.app.dao.StudentDao;
import project.util.BreadcrumbPrompt;

public class StudentDeleteListener implements StudentActionListener {

  StudentDao studentDao;

  public StudentDeleteListener(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (studentDao.delete(prompt.inputInt("학번? ")) == 0) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }
  }
}











