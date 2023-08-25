package project.app.dao;

import project.app.vo.Lecture;

import java.util.List;

public interface LectureDao {
    void insert(Lecture lecture);
    List<Lecture> findAll();
    List<Lecture> findBySubject(int subjectNo);
    Lecture findByNo(int lectNo);
    int update (Lecture lecture);
    int delete (int lectNo);
}
