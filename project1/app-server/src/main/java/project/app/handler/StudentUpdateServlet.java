package project.app.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/student/update")
public class StudentUpdateServlet implements Servlet {

  StudentDao studentDao;
  SqlSessionFactory sqlSessionFactory;

  public StudentUpdateServlet(StudentDao studentDao, SqlSessionFactory sqlSessionFactory) {
    this.studentDao = studentDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Student std = new Student();
    std.setNo(Integer.parseInt(request.getParameter("no")));
    std.setName(request.getParameter("name"));
    std.setEmail(request.getParameter("email"));
    std.setPassword(request.getParameter("password"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/student/list'>");
    out.println("<title>학생</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생 정보 변경</h1>");

    try {
      if (studentDao.update(std) == 0) {
        out.println("<p>학생이 없습니다.</p>");
      } else {
        sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}











