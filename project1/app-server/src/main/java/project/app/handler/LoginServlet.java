package project.app.handler;

import java.io.PrintWriter;
import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/auth/login")
public class LoginServlet implements Servlet {

  StudentDao studentDao;

  public LoginServlet(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Student std = new Student();
    std.setEmail(request.getParameter("email"));
    std.setPassword(request.getParameter("password"));

    Student loginUser = studentDao.findByEmailAndPassword(std);
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