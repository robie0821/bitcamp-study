package project.app.handler;

import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ReviewDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/review/delete")
public class ReviewDeleteServlet implements Servlet {

  ReviewDao reviewDao;
  SqlSessionFactory sqlSessionFactory;

  public ReviewDeleteServlet(ReviewDao reviewDao, SqlSessionFactory sqlSessionFactory) {
    this.reviewDao = reviewDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      if (reviewDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 강의평가가 없습니다.");
      } else {
        response.sendRedirect("/review/list");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}