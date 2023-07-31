package project.app.handler;

import java.io.IOException;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class ReviewSearchListener implements ActionListener {

  ReviewDao reviewDao;

  public ReviewSearchListener(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Review rev = reviewDao.findBy(prompt.inputInt("학번? "), prompt.inputInt("과목코드? "));
    if (rev == null) {
      System.out.println("해당 정보의 없습니다.");
      return;
    }
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











