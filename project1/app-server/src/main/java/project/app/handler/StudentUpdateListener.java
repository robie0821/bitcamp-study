package project.app.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class StudentUpdateListener implements ActionListener {

  StudentDao studentDao;
  SqlSessionFactory sqlSessionFactory;

  public StudentUpdateListener(StudentDao studentDao, SqlSessionFactory sqlSessionFactory) {
    this.studentDao = studentDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int studentNo = prompt.inputInt("학번? ");

    Student std = studentDao.findBy(studentNo);
    if (std == null) {
      System.out.println("해당 학번의 학생이 존재하지 않습니다.");
      return;
    }

    std.setName(prompt.inputString("이름(%s)? ", std.getName()));
    std.setEmail(prompt.inputString("이메일(%s)? ", std.getEmail()));

    try {
      studentDao.update(std);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}











