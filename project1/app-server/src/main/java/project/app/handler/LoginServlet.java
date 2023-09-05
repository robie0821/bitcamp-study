package project.app.handler;

import project.app.vo.Member;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member member = new Member();
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));

    Member loginUser = InitServlet.memberDao.findByEmailAndPassword(member);
    if (loginUser != null) {
      request.getSession().setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
      return;
    }

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/auth/form.html'>");
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인</h1>");
    out.println("<p>회원 정보가 일치하지 않습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }
}