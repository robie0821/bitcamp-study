package project.app.vo;

import java.io.Serializable;

public class Review implements Serializable{
  private static final long serialVersionUID = 1L;

  private Student student;

  private int no;
  private int subjectId;
  private int rate;
  private String content;

  public Student getStudent() {
    return student;
  }
  public void setStudent(Student student) {
    this.student = student;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getSubjectId() {
    return subjectId;
  }
  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }
  public int getRate() {
    return rate;
  }
  public void setRate(int rate) {
    this.rate = rate;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
}