package project.app.vo;

import java.io.Serializable;

public class Department implements Serializable {
  private static final long serialVersionUID = 1L;
  private int deptNo;
  private String name;

  public int getDeptNo() {
    return deptNo;
  }

  public void setDeptNo(int deptNo) {
    this.deptNo = deptNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
