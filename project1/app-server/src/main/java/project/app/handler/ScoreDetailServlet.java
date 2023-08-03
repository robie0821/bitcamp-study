package project.app.handler;

import org.apache.ibatis.session.SqlSessionFactory;
import project.app.dao.ScoreDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/score/detail")
public class ScoreDetailServlet implements Servlet {

  ScoreDao scoreDao;
  SqlSessionFactory sqlSessionFactory;

  public ScoreDetailServlet(ScoreDao scoreDao, SqlSessionFactory sqlSessionFactory) {
    this.scoreDao = scoreDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

  }

}
