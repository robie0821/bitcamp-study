package project.app.dao;

public interface MemberDao {
    void insert(Member member);
    List<Member>findAll(int type);
    Member findBy(int no);
    Member findByEmailAndPassword(Member member);
    int update(member member);
    int delete(int no);
}
