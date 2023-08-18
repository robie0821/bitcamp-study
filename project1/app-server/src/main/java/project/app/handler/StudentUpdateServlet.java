package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
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
      out.println("<p>본인 정보만 변경 가능합니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=/index.html'>");
      return;
    }

    Student std = new Student();
    std.setNo(Integer.parseInt(request.getParameter("no")));
    std.setName(request.getParameter("name"));
    std.setEmail(request.getParameter("email"));
    std.setPassword(request.getParameter("password"));

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
      if (InitServlet.studentDao.update(std) == 0) {
        out.println("<p>학생이 없습니다.</p>");
      } else {
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}