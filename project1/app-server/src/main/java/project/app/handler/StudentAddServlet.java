package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.app.vo.Student;

@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    Student student = new Student();
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    if (name.length() == 0 || email.length() == 0) {
      out.println("<p>이름이나 이메일에는 공백이 올 수 없습니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=/student/list'>");
      return;
    }
    student.setName(name);
    student.setEmail(email);
    student.setPassword(request.getParameter("password"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/student/list'>");
    out.println("<title>학생</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생 등록</h1>");

    try {
      InitServlet.studentDao.insert(student);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}