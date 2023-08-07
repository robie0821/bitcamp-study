package project.app.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.app.vo.Student;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/review/add")
public class ReviewAddServlet implements Servlet {

  ReviewDao reviewDao;
  SqlSessionFactory sqlSessionFactory;

  public ReviewAddServlet(ReviewDao reviewDao, SqlSessionFactory sqlSessionFactory) {
    this.reviewDao = reviewDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
      reviewDao.insert(rev);
      sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
    out.println("</body>");
    out.println("</html>");
  }
}