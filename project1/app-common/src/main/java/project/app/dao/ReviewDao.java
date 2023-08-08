package project.app.dao;

import java.util.List;
import java.util.Map;
import project.app.vo.Review;

public interface ReviewDao {
  void insert(Review review);
  List<Map<String,Object>> list();
  List<Review> findBySubject(int subject);
  Review findBy(int no);
  int update(Review review);
  int delete(int no);
}