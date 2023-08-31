package project.app.dao;

import project.app.vo.ProfLect;

import java.util.List;

public interface ProfLectDao {
  void insert(ProfLect profLect);
  List<ProfLect> findAll(int profNo);
  ProfLect findBy(int profNo, int lectNo);
  int delete(int profNo, int lectNo);
}