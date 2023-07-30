package project.app.handler;

import java.io.IOException;
import java.util.List;
import project.app.dao.ScoreDao;
import project.app.vo.Score;
import project.util.BreadcrumbPrompt;

public class ScoreListListener implements ScoreActionListener {

  ScoreDao scoreDao;
  
  public ScoreListListener(ScoreDao scoreDao) {
    this.scoreDao = scoreDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    System.out.println("--------------------------------------------------");
    System.out.println("번호, 이름, C++, Java, Python, Linux, 학점, 장학금");
    System.out.println("--------------------------------------------------");

    List<Score> list = scoreDao.findAll();
    
    for (Score s : list) {
      prompt.printf("%d, %s, %s, %s, %s, %s, %.2f, %b\n",
          s.getStudent().getNo(),
          s.getStudent().getName(),
          s.getSub1(),
          s.getSub2(),
          s.getSub3(),
          s.getSub4(),
          s.getGrade(),
          s.isScholar());
    }
  }
}
