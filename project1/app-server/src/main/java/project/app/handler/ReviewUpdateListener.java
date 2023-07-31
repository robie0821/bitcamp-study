package project.app.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class ReviewUpdateListener implements ActionListener {

  ReviewDao reviewDao;
  SqlSessionFactory sqlSessionFactory;

  public ReviewUpdateListener(ReviewDao reviewDao, SqlSessionFactory sqlSessionFactory) {
    this.reviewDao = reviewDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Review rev = reviewDao.findBy(prompt.inputInt("학번? "), prompt.inputInt("1. C++\\n2. Java\\n3. Python\\n4.Linux\\n과목코드? "));
    if (rev == null) {
      System.out.println("입력한 정보의 리뷰가 없습니다.");
      return;
    }

    rev.setRate(prompt.inputInt("평점(%d)? ", rev.getRate()));
    rev.setContent(prompt.inputString("내용(%s)? ", rev.getContent()));

    try {
      if (reviewDao.update(rev) == 0) {
        prompt.println("게시글 변경 권한이 없습니다.");
      } else {
        prompt.println("변경했습니다!");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}