package project.app.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.app.vo.Student;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class ReviewAddServlet implements ActionListener {

  ReviewDao reviewDao;
  SqlSessionFactory sqlSessionFactory;

  public ReviewAddServlet(ReviewDao reviewDao, SqlSessionFactory sqlSessionFactory) {
    this.reviewDao = reviewDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Review rev = new Review();
    rev.setSubjectId(prompt.inputInt("과목코드?\n1. C++\n2. 자바\n3. 파이썬\n4. 리눅스\n"));
    rev.setRate(prompt.inputInt("평점?"));
    rev.setContent(prompt.inputString("내용?"));
    rev.setStudent((Student) prompt.getAttribute("loginUser"));

    try {
      reviewDao.insert(rev);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}











