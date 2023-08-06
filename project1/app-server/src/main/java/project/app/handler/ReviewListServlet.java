package project.app.handler;

import java.io.IOException;
import java.util.List;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

public class ReviewListServlet implements Servlet {

  ReviewDao reviewDao;

  public ReviewListServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    System.out.printf("1. C++\n2. Java\n3. Python\n4. Linux\n");
    List<Review> list = reviewDao.findAll(Integer.parseInt(request.getParameter("subjectId")));

    for (Review rev : list) {
      prompt.printf("평점 : ");
      for (int i = 0; i < rev.getRate(); i++) {
        prompt.printf("★");
      }
      for (int i = 0; i < 5 - rev.getRate(); i++) {
        prompt.printf("★");
      }
      prompt.printf("\n평가 : %s\n", rev.getContent());
    }
  }
}











