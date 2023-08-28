package project.app.dao;

import project.app.vo.ProfessorLecture;

import java.util.List;

public interface ProfessorLectureDao {
  void insert(ProfessorLecture professorLecture);
  List<ProfessorLecture> findAll(int profNo);
  ProfessorLecture findBy(int profNo, int lectNo);
  int delete(int profNo, int lectNo);
}
