package project.app.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ScoreDao;
import project.app.vo.Score;
import project.util.BreadcrumbPrompt;

public class ScoreAddListener implements ScoreActionListener {

  ScoreDao scoreDao;
  SqlSessionFactory sqlSessionFactory;
  
  public ScoreAddListener(ScoreDao scoreDao, SqlSessionFactory sqlSessionFactory) {
    this.scoreDao = scoreDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Score s = new Score();
    s.setSub1(ScoreActionListener.inputScore(prompt, "C++? "));
    s.setSub2(ScoreActionListener.inputScore(prompt, "Java? "));
    s.setSub3(ScoreActionListener.inputScore(prompt, "Python? "));
    s.setSub4(ScoreActionListener.inputScore(prompt, "Linux? "));
    s.compute();
    
    try {
      scoreDao.insert(s);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
