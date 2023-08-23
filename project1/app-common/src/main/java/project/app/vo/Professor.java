package project.app.vo;

import java.io.Serializable;

public class Professor implements Serializable {
  private static final long serialVersionUID = 1L;
  private Member professor;

  public Member getProfessor() {
    return professor;
  }

  public void setProfessor(Member professor) {
    this.professor = professor;
  }
}
