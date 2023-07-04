package project.myapp.vo;

import java.io.Serializable;

public class Student implements Serializable, CsvObject{
  private static final long serialVersionUID = 1L;

  private int no;
  private String name;
  private String password;

  private String cpp;
  private String java;
  private String python;
  private String linux;

  private String cppReview;
  private String javaReview;
  private String pythonReview;
  private String linuxReview;

  private double grade;
  private boolean scholar;

  public static Student fromCsv(String csv) {
    String[] values = csv.split(",");

    Student student = new Student();
    student.setNo(Integer.parseInt(values[0]));
    student.setName(values[1]);
    student.setPassword(values[2]);
    student.setCpp(values[3]);
    student.setJava(values[4]);
    student.setPython(values[5]);
    student.setLinux(values[6]);
    student.setCppReview(values[7]);
    student.setJavaReview(values[8]);
    student.setPythonReview(values[9]);
    student.setLinuxReview(values[10]);
    student.setGrade(Double.parseDouble(values[11]));
    student.setScholar(Boolean.parseBoolean(values[12]));

    return student;
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%.2f,%b",
        this.getNo(),
        this.getName(),
        this.getPassword(),
        this.getCpp(),
        this.getJava(),
        this.getPython(),
        this.getLinux(),
        this.getCppReview(),
        this.getJavaReview(),
        this.getPythonReview(),
        this.getLinuxReview(),
        this.getGrade(),
        this.isScholar());
  }

  public Student() {

  }

  public Student(int no) {
    this.no = no;
  }

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCpp() {
    return cpp;
  }

  public void setCpp(String cpp) {
    this.cpp = cpp;
  }

  public String getJava() {
    return java;
  }

  public void setJava(String java) {
    this.java = java;
  }

  public String getPython() {
    return python;
  }

  public void setPython(String python) {
    this.python = python;
  }

  public String getLinux() {
    return linux;
  }

  public void setLinux(String linux) {
    this.linux = linux;
  }

  public String getCppReview() {
    return cppReview;
  }

  public void setCppReview(String cppReview) {
    this.cppReview = cppReview;
  }

  public String getJavaReview() {
    return javaReview;
  }

  public void setJavaReview(String javaReview) {
    this.javaReview = javaReview;
  }

  public String getPythonReview() {
    return pythonReview;
  }

  public void setPythonReview(String pythonReview) {
    this.pythonReview = pythonReview;
  }

  public String getLinuxReview() {
    return linuxReview;
  }

  public void setLinuxReview(String linuxReview) {
    this.linuxReview = linuxReview;
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
    String[] score = {this.getCpp(), this.getJava(), this.getPython(), this.getLinux()};
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
