package project.app.handler;

import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.StudentDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/student/delete")
public class StudentDeleteServlet implements Servlet {

  StudentDao studentDao;
  SqlSessionFactory sqlSessionFactory;

  public StudentDeleteServlet(StudentDao studentDao, SqlSessionFactory sqlSessionFactory) {
    this.studentDao = studentDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      if (studentDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } else {
        response.sendRedirect("/student/list");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}