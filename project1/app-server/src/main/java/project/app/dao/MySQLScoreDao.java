package project.app.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.Score;
import project.util.Component;

@Component
public class MySQLScoreDao implements ScoreDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLScoreDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Score score) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.ScoreDao.insert", score);
  }

  @Override
  public List<Score> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.ScoreDao.findAll");
  }

  @Override
  public Score findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("project.app.dao.ScoreDao.findBy", no);
  }

  @Override
  public int update(Score score) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.dao.ScoreDao.update", score);
  }
}