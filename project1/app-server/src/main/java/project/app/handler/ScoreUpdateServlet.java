package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.app.vo.Score;
import project.app.vo.Student;

@WebServlet("/score/update")
public class ScoreUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    Student loginUser = (Student) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<p>로그인이 필요합니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=/auth/form.html'>");
      return;
    }

    if (loginUser.getNo() != Integer.parseInt(request.getParameter("student")) ) {
      out.println("<p>본인 점수만 변경 가능합니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=/index.html'>");
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
      if (InitServlet.scoreDao.update(s) == 0) {
        out.println("<p>변경 권한이 없습니다.</p>");
      } else {
        out.println("<p>변경했습니다!</p>");
      }
      InitServlet.sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}