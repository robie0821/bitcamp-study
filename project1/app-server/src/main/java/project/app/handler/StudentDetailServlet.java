package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student/detail")
public class StudentDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    Student loginUser = (Student) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<p>로그인이 필요합니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=/auth/form.html'>");
      return;
    }

    Student std = InitServlet.studentDao.findBy(Integer.parseInt(request.getParameter("no")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>학생</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생</h1>");
    out.printf("<h4>현재 사용자 : %s</h4>\n", loginUser.getEmail());

    if (std == null) {
      out.println("<p>유효하지 않은 학번입니다!</p>");

    } else {
      out.println("<form action='/student/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n", std.getNo());
      out.printf("<tr><th>이름</th>"
          + " <td><input type='text' name='name' value='%s'></td></tr>\n", std.getName());
      out.printf("<tr><th>이메일</th>"
          + " <td><input type='email' name='email' value='%s'></td></tr>\n", std.getEmail());
      out.println("<tr><th>암호</th>"
          + " <td><input type='password' name='password'></td></tr>");
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/student/delete?no=%d'>삭제</a>\n", std.getNo());
      out.println("<a href='/student/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}