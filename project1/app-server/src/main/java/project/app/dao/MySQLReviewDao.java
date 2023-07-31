package project.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.Review;

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
  public List<Review> findAll(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.ReviewDao.findAll", no);
  }

  @Override
  public Review findBy(int student, int subject) {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("StudentNo", student);
    paramMap.put("subjectNo", subject);

    return sqlSession.selectOne("project.app.dao.ReviewDao.findBy", paramMap);
  }

  @Override
  public int update(Review review) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.dao.ReviewDao.update", review);
  }

  @Override
  public int delete(Review review) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.app.dao.ReviewDao.delete", review);
  }
}
