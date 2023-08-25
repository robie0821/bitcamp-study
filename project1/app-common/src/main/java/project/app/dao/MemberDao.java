package project.app.dao;

import project.app.vo.Member;

import java.util.List;

public interface MemberDao {
    void insert(Member member);
    List<Member> findAll(int type);
    Member findBy(int memberNo);
    Member findByEmailAndPassword(Member member);
    int update(Member member);
    int delete(int memberNo);
}
