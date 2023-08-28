package project.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.ProfessorLecture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLProfessorLectureDao implements ProfessorLectureDao{
  SqlSessionFactory sqlSessionFactory;

  public MySQLProfessorLectureDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(ProfessorLecture professorLecture) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.ProfessorLectureDao.insert", professorLecture);
  }

  @Override
  public List<ProfessorLecture> findAll(int profNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.ProfessorLectureDao.findAll", profNo);
  }

  @Override
  public ProfessorLecture findBy(int profNo, int lectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    Map<String, Integer> map = new HashMap<>();
    map.put("profNo", profNo);
    map.put("lectNo", lectNo);
    return sqlSession.selectOne("project.app.ProfessorLectureDao.findBy", map);
  }

  @Override
  public int delete(int profNo, int lectNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    Map<String, Integer> map = new HashMap<>();
    map.put("profNo", profNo);
    map.put("lectNo", lectNo);
    return sqlSession.delete("project.app.ProfessorLectureDao.delete", map);
  }
}