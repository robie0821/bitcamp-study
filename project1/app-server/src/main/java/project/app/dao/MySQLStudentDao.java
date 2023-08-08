package project.app.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.Student;

public class MySQLStudentDao implements StudentDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLStudentDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Student student) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.StudentDao.insert", student);
  }

  @Override
  public List<Student> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.StudentDao.findAll");
  }

  @Override
  public Student findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.app.dao.StudentDao.findBy", no);
  }

  @Override
  public Student findByEmailAndPassword(Student student) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.app.dao.StudentDao.findByEmailAndPassword", student);
  }

  @Override
  public int update(Student student) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.dao.StudentDao.update", student);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.app.dao.StudentDao.delete", no);
  }
}