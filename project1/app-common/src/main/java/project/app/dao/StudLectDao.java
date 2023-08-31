package project.app.dao;

import project.app.vo.StudLect;

import java.util.List;

public interface StudLectDao {
  void insert(StudLect studLect);
  List<StudLect> findAll(int studNo);
  ProfLectDao findBy(int studNo, int lectNo);
  int delete(int studNo, int lectNo);
}