package project.app.dao;

import java.util.List;
import project.app.vo.Review;

public interface ReviewDao {
  void insert(Review review);
  List<Review> findAll(int no);
  Review findBy(int no1, int no2);
  int update(Review review);
  int delete(Review review);
}
