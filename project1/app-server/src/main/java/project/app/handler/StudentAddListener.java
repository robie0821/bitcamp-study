package project.app.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class StudentAddListener implements ActionListener {

  StudentDao studentDao;
  SqlSessionFactory sqlSessionFactory;

  public StudentAddListener(StudentDao studentDao, SqlSessionFactory sqlSessionFactory) {
    this.studentDao = studentDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Student student = new Student();
    student.setName(prompt.inputString("이름? "));
    student.setEmail(prompt.inputString("이메일? "));
    student.setPassword(prompt.inputString("비밀번호? "));

    try {
      studentDao.insert(student);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}