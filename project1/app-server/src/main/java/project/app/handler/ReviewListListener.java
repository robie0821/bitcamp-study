package project.app.handler;

import java.io.IOException;
import java.util.List;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class ReviewListListener implements ActionListener {

  ReviewDao reviewDao;

  public ReviewListListener(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    System.out.printf("1. C++\n2. Java\n3. Python\n4. Linux\n");
    List<Review> list = reviewDao.findAll(prompt.inputInt("과목?"));

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











