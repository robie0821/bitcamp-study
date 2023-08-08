package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.app.vo.Student;

@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>학생</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/student/form.html'>새 학생</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th style='width:50px;'>번호</th> <th style='width:80px;'>이름</th> <th style='width:150px;'>이메일</th></tr>");
    out.println("</thead>");

    List<Student> list = InitServlet.studentDao.findAll();
    for (Student std : list) {
      out.printf("<tr>"
          + " <td><center>%d</center></td>"
          + " <td style='padding:0 0 0 5px'><a href='/student/detail?no=%d'>%s</td>"
          + " <td style='padding:0 0 0 5px'>%s</td></tr>\n",
          std.getNo(), std.getNo(), std.getName(), std.getEmail());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}