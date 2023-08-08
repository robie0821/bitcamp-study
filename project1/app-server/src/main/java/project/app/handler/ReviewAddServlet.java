package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.app.vo.Review;
import project.app.vo.Student;

@WebServlet("/review/add")
public class ReviewAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Student loginUser = (Student) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    Review rev = new Review();
    rev.setStudent(loginUser);
    rev.setSubjectId(Integer.parseInt(request.getParameter("subject")));
    rev.setRate(Integer.parseInt(request.getParameter("rate")));
    rev.setContent(request.getParameter("content"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/review/list'>");
    out.println("<title>강의평가</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의평가 등록</h1>");


    try {
      InitServlet.reviewDao.insert(rev);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
    out.println("</body>");
    out.println("</html>");
  }
}