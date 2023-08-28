package project.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.StudentLecture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLStudentLectureDao implements StudentLectureDao{
  SqlSessionFactory sqlSessionFactory;

  public MySQLStudentLectureDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(StudentLecture studentLecture) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.StudentLectureDao.insert", studentLecture);
  }

  @Override
  public List<StudentLecture> findAll(int studNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.StudentLectureDao.findAll", studNo);
  }

  @Override
  public ProfessorLectureDao findBy(int studNo, int lectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    Map<String, Integer> map = new HashMap<>();
    map.put("studNo", studNo);
    map.put("lectNo", lectNo);
    return sqlSession.selectOne("project.app.StudentLectureDao.findBy", map);
  }

  @Override
  public int delete(int studNo, int lectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    Map<String, Integer> map = new HashMap<>();
    map.put("studNo", studNo);
    map.put("lectNo", lectNo);
    return sqlSession.delete("project.app.StudentLectureDao.delete", map);
  }
}