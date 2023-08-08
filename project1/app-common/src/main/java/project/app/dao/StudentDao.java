package project.app.dao;

import java.util.List;
import project.app.vo.Student;

public interface StudentDao {
  void insert(Student student);
  List<Student> findAll();
  Student findBy(int no);
  Student findByEmailAndPassword(Student student);
  int update(Student student);
  int delete(int no);
}