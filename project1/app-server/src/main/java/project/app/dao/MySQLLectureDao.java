package project.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.Lecture;

public class MySQLLectureDao implements LectureDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLLectureDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Lecture lecture) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.LectureDao.insert", lecture);
  }

  @Override
  public List<Map<String,Object>> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.LectureDao.findAll");
  }

  @Override
  public List<Lecture> findBySubject(int subjectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.LectureDao.findBySubject", subjectNo);
  }

  @Override
  public Lecture findByNo(int lectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("project.app.dao.LectureDao.findByNo", lectNo);
  }

  @Override
  public int update(Lecture lecture) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.dao.LectureDao.update", lecture);
  }

  @Override
  public int delete(int lectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.app.dao.LectureDao.delete", lectNo);
  }
}