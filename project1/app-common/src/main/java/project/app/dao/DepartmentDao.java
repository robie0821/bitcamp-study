package project.app.dao;

import project.app.vo.Department;

import java.util.List;

public interface DepartmentDao {
  void insert (Department department);
  List<Department> findAll();
  Department findBy(int deptNo);
  int update(Department department);
  int delete(int deptNo);
}
