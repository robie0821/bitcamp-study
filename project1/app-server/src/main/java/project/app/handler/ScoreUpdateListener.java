package project.app.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ScoreDao;
import project.app.vo.Score;
import project.util.BreadcrumbPrompt;

public class ScoreUpdateListener implements ScoreActionListener {

  ScoreDao scoreDao;
  SqlSessionFactory sqlSessionFactory;
  
  public ScoreUpdateListener(ScoreDao scoreDao, SqlSessionFactory sqlSessionFactory) {
    this.scoreDao = scoreDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Score s = scoreDao.findBy(prompt.inputInt("학번? "));
    if (s == null) {
      System.out.println("해당 학번의 학생이 없습니다!");
      return;
    }

    s.setSub1(ScoreActionListener.inputScore(prompt, "C++(" + s.getSub1() + ")? "));
    s.setSub2(ScoreActionListener.inputScore(prompt, "Java(" + s.getSub2() + ")? "));
    s.setSub3(ScoreActionListener.inputScore(prompt, "Python(" + s.getSub3() + ")? "));
    s.setSub4(ScoreActionListener.inputScore(prompt, "Linux(" + s.getSub4() + ")? "));
    s.compute();
    
    try {
      if (scoreDao.update(s) == 0) {
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
