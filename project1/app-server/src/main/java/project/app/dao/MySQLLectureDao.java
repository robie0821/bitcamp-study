package project.app.dao;

import java.util.List;
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
  public List<Lecture> findAll(int subject) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.LectureDao.findAll", subject);
  }

  @Override
  public Lecture findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("project.app.dao.LectureDao.findBy", no);
  }

  @Override
  public int update(Lecture lecture) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.dao.LectureDao.update", lecture);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.app.dao.LectureDao.delete", no);
  }
}