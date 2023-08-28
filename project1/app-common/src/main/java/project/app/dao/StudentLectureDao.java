package project.app.dao;

import project.app.vo.StudentLecture;

import java.util.List;

public interface StudentLectureDao {
  void insert(StudentLecture studentLecture);
  List<StudentLecture> findAll(int studNo);
  ProfessorLectureDao findBy(int studNo, int lectNo);
  int delete(int studNo, int lectNo);
}
