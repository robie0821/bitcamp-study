package project.app.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.app.vo.Member;

public class MySQLMemberDao implements MemberDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLMemberDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.app.dao.MemberDao.insert", member);
  }

  @Override
  public List<Member> findAll(int type) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("project.app.dao.MemberDao.list", type);
  }

  @Override
  public Member findBy(int memberNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.app.dao.MemberDao.detail", memberNo);
  }

  @Override
  public Member findByEmailAndPassword(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.app.dao.MemberDao.signIn", member);
  }

  @Override
  public int update(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.app.dao.MemberDao.update", member);
  }

  @Override
  public int delete(int memberNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.app.dao.MemberDao.delete", memberNo);
  }
}