package project.app.dao;

import project.app.vo.Lecture;

import java.util.List;

public interface LectureDao {
    void insert(Lecture lecture);
    List<Lecture> findAll(int subject);
    Lecture findBy(int no);
    int update (Lecture lecture);
    int delete (int no);
}
