package project.app.dao;

import java.util.List;
import project.app.vo.Review;

public interface ReviewDao {
  void insert(Review review);
  List<Review> findAll();
  List<Review> findBySubject(int subject);
  Review findBy(int no);
  int update(Review review);
  int delete(int no);
}