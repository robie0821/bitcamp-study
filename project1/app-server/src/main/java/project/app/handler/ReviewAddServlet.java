package project.app.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.app.vo.Student;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

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
    rev.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
    rev.setRate(Integer.parseInt(request.getParameter("rate")));
    rev.setContent(request.getParameter("content"));
    rev.setStudent(loginUser);

    try {
      reviewDao.insert(rev);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}











