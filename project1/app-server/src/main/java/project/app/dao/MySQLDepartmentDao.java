package project.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.Department;

import java.util.List;

public class MySQLDepartmentDao implements DepartmentDao {
  SqlSessionFactory sqlSessionFactory;
  public MySQLDepartmentDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Department department) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.DepartmentDao.insert", department);
  }

  @Override
  public List<Department> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.app.dao.DepartmentDao.findAll");
  }

  @Override
  public Department findBy(int deptNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("project.app.DepartmentDao.findBy");
  }

  @Override
  public int update(Department department) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.DepartmentDao.update", department);
  }

  @Override
  public int delete(int deptNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.app.DepartmentDao.delete", deptNo);
  }
}