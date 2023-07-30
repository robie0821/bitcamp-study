package project.app.handler;

import java.io.IOException;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.app.vo.Student;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class ReviewAddListener implements ActionListener {

  ReviewDao reviewDao;
  SqlSessionFactory sqlSessionFactory;
  
  public ReviewAddListener(ReviewDao reviewDao, SqlSessionFactory sqlSessionFactory) {
    this.reviewDao = reviewDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Review rev = reviewDao.findBy(prompt.inputInt("학번? "));
    if (rev == null) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }
    rev.setStudent((Student) prompt.getAttribute("loginUser"));
    rev.setSubjectId(prompt.inputInt("Java 강의평가? "));
    rev.setRate(prompt.inputInt("Python 강의평가? "));
    rev.setContent(prompt.inputString("Linux 강의평가? "));
  }
}











