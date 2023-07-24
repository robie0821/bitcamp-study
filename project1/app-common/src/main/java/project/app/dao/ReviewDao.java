package project.app.dao;

import java.util.List;
import project.app.vo.Review;

public interface ReviewDao {
  void insert(Review board);
  List<Review> list();
  Review findBy(int no);
  int update(Review board);
  int delete(Review board);
}
