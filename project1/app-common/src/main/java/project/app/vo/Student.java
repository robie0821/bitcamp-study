package project.app.vo;

import java.io.Serializable;

public class Student implements Serializable {
  private static final long serialVersionUID = 1L;

  private Member student;

  public Member getStudent() {
    return student;
  }

  public void setStudent(Member student) {
    this.student = student;
  }
}
