package project.app.dao;

import project.app.vo.Lecture;

import java.util.List;
import java.util.Map;

public interface LectureDao {
    void insert(Lecture lecture);
    List<Map<String,Object>> findAll();
    List<Lecture> findBySubject(int subjectNo);
    Lecture findByNo(int lectNo);
    int update (Lecture lecture);
    int delete (int lectNo);
}
