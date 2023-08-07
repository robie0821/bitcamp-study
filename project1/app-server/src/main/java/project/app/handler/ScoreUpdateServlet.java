package project.app.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ScoreDao;
import project.app.vo.Score;
import project.app.vo.Student;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/score/update")
public class ScoreUpdateServlet implements Servlet {

  ScoreDao scoreDao;
  SqlSessionFactory sqlSessionFactory;

  public ScoreUpdateServlet(ScoreDao scoreDao, SqlSessionFactory sqlSessionFactory) {
    this.scoreDao = scoreDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Student loginUser = (Student) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    Score s = new Score();
    s.setStudent(loginUser);
    s.setNo(Integer.parseInt(request.getParameter("no")));
    s.setSub1(request.getParameter("sub1"));
    s.setSub2(request.getParameter("sub2"));
    s.setSub3(request.getParameter("sub3"));
    s.setSub4(request.getParameter("sub4"));
    s.compute();

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/score/list'>");
    out.println("<title>학점</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학점 변경</h1>");

    try {
      if (scoreDao.update(s) == 0) {
        out.println("<p>변경 권한이 없습니다.</p>");
      } else {
        out.println("<p>변경했습니다!</p>");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}