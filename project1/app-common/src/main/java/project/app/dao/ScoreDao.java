package project.app.dao;

import java.util.List;
import project.app.vo.Score;

public interface ScoreDao {
  void insert(Score score);
  List<Score> findAll();
  Score findBy(int no);
  int update(Score score);
}
