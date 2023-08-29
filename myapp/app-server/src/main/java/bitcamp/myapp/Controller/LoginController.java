package bitcamp.myapp.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    request.getRequestDispatcher("/auth/form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member m = new Member();
    m.setEmail(request.getParameter("email"));
    m.setPassword(request.getParameter("password"));

    if (request.getParameter("saveEmail") != null) {
      Cookie cookie = new Cookie("email", m.getEmail());
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    Member loginUser = memberDao.findByEmailAndPassword(m);
    if (loginUser != null) {
      request.getSession().setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
      return;
    }
    request.setAttribute("refresh", "2;url=/auth/login");
    throw new ServletException("회원 정보가 일치하지 않습니다.");
  }
}