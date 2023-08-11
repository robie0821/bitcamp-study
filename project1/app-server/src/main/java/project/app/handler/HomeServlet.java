package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.app.vo.Student;

@WebServlet("/index.html")
public class HomeServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Student loginUser = (Student) request.getSession().getAttribute("loginUser");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>**대학교</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>**대학교 행정시스템</h1>");
    if (loginUser == null) {
      out.printf("<h4>비로그인</h4>\n");
    } else {
      out.printf("<h4>현재 사용자 : %s</h4>\n", loginUser.getEmail());
    }
    out.println("<ul>");
    out.println("  <li><a href='/student/list'>학생</a></li>");
    out.println("  <li><a href='/score/list'>성적</a></li>");
    out.println("  <li><a href='/review/list'>강의평가</a></li>");
    if (loginUser == null) {
      out.println("  <li><a href='/auth/form.html'>로그인</a></li>");
    } else {
      out.printf("  <li><a href='/auth/logout'>로그아웃</a></li>", loginUser.getName());
    }
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
  }
}
