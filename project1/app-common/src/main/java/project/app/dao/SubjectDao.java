package project.app.dao;

import project.app.vo.Subject;

public interface SubjectDao {
    void insert(Subject subject);
    List<Subject> findAll();
    int update(Subject subject);
    int delete(int no);
}
