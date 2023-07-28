package project.app.handler;

import java.util.List;
import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentListListener implements StudentActionListener {

  StudentDao studentDao;

  public StudentListListener(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("학번, 이름, 이메일");
    System.out.println("---------------------------------------");

    List<Student> list = studentDao.findAll();
    for (Student student : list) {
      System.out.printf("%d, %s, %s\n",
          student.getNo(),
          student.getName(),
          student.getEmail());
    }
  }
}