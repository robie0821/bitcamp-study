package project.app.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.StudentDao;
import project.util.BreadcrumbPrompt;

public class StudentDeleteListener implements StudentActionListener {

  StudentDao studentDao;
  SqlSessionFactory sqlSessionFactory;

  public StudentDeleteListener(StudentDao studentDao, SqlSessionFactory sqlSessionFactory) {
    this.studentDao = studentDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException  {
    try {
      if (studentDao.delete(prompt.inputInt("학번? ")) == 0) {
        System.out.println("해당 학번의 학생이 없습니다!");
      }
      prompt.println("삭제했습니다!");
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}