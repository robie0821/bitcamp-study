package project.app.vo;

import java.io.Serializable;

public class Student implements Serializable{
  private static final long serialVersionUID = 1L;

  private int no;
  private String name;
  private String email;
  private String password;

  private String sub1;
  private String sub2;
  private String sub3;
  private String sub4;

  private double grade;
  private boolean scholar;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getSub1() {
    return sub1;
  }
  public void setSub1(String sub1) {
    this.sub1 = sub1;
  }
  public String getSub2() {
    return sub2;
  }
  public void setSub2(String sub2) {
    this.sub2 = sub2;
  }
  public String getSub3() {
    return sub3;
  }
  public void setSub3(String sub3) {
    this.sub3 = sub3;
  }
  public String getSub4() {
    return sub4;
  }
  public void setSub4(String sub4) {
    this.sub4 = sub4;
  }
  public double getGrade() {
    return grade;
  }
  public void setGrade(double grade) {
    this.grade = grade;
  }
  public boolean isScholar() {
    return scholar;
  }
  public void setScholar(boolean scholar) {
    this.scholar = scholar;
  }

  public void compute() {
    String[] score = {this.getSub1(), this.getSub2(),
        this.getSub3(), this.getSub4()};
    double grade = 0.0;
    for (int i = 0; i < score.length; i++) {
      switch(score[i]) {
        case "A+": grade += 4.5; break;
        case "A": grade += 4.0; break;
        case "B+": grade += 3.5; break;
        case "B": grade += 3.0; break;
        case "C+": grade += 2.5; break;
        case "C": grade += 2.0; break;
        case "D+": grade += 1.5; break;
        case "D": grade += 1.0; break;
        case "F": grade += 0.0; break;
      }
    }
    grade = Math.round(grade / score.length * 100.0) / 100.0;
    this.setGrade(grade);
    this.setScholar(grade >= 3.0);
  }
}
