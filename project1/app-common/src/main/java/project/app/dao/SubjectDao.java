package project.app.dao;

import project.app.vo.Subject;

import java.util.List;

public interface SubjectDao {
    void insert(Subject subject);
    List<Subject> findAll();
    Subject findBy(int subjectNo);
    int update(Subject subject);
    int delete(int subjectNo);
}
