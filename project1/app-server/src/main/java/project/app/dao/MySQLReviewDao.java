package project.app.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.Review;
import project.util.Component;

@Component
public class MySQLReviewDao implements ReviewDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLReviewDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Review review) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.ReviewDao.insert", review);
  }

  @Override
  public List<Review> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.ReviewDao.findAll");
  }

  @Override
  public List<Review> findBySubject(int subject) {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    return sqlSession.selectList("project.app.dao.ReviewDao.findBySubject", subject);
  }

  @Override
  public Review findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.app.dao.ReviewDao.findBy", no);
  }

  @Override
  public int update(Review review) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.dao.ReviewDao.update", review);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.app.dao.ReviewDao.delete", no);
  }
}
