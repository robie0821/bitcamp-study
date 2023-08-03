package project.app.handler;

import java.io.PrintWriter;
import java.util.List;
import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/student/list")
public class StudentListServlet implements Servlet {

  StudentDao studentDao;

  public StudentListServlet(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    out.println("  <tr><th>번호</th> <th>이름</th> <th>이메일</th></tr>");
    out.println("</thead>");

    List<Student> list = studentDao.findAll();
    for (Student std : list) {
      out.printf("<tr>"
          + " <td>%d</td>"
          + " <td><a href='/student/detail?no=%d'>%s</td>"
          + " <td>%s</td></tr>\n",
          std.getNo(), std.getNo(), std.getName(), std.getEmail());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}