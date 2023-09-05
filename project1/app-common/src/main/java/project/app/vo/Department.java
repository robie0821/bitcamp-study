package project.app.vo;

import java.io.Serializable;

public class Department implements Serializable {
  private static final long serialVersionUID = 1L;
  private int deptNo;
  private String deptName;

  public Department() {
  }

  public Department(int deptNo) {
    this.deptNo = deptNo;
  }

  public int getDeptNo() {
    return deptNo;
  }

  public void setDeptNo(int deptNo) {
    this.deptNo = deptNo;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
}