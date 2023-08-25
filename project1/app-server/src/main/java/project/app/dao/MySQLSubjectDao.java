package project.app.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.Subject;

public class MySQLSubjectDao implements SubjectDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLSubjectDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Subject subject) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.SubjectDao.insert", subject);
  }

  @Override
  public List<Subject> list() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("project.app.dao.SubjectDao.list");
  }

  @Override
  public Subject detail(int subjectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.app.dao.SubjectDao.detail", subjectNo);
  }

  @Override
  public int update(Subject subject) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.dao.SubjectDao.update", subject);
  }

  @Override
  public int delete(int subjectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.app.dao.SubjectDao.delete", subjectNo);
  }
}