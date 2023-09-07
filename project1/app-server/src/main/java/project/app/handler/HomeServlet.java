package project.app.handler;

import project.app.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index.html")
public class HomeServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>**대학교</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>**대학교 행정시스템</h1>");
    out.println("<ul>");
    out.println("<li/><a href='/lecture/list'/>수강신청");
    if (loginUser == null) {
      out.println("<li/><a href='/auth/form.html'/>로그인");
    } else {
      out.printf("<li/><a href='/auth/logout'/>로그아웃", loginUser.getMemberName());
    }
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
  }
}