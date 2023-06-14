package project1.vo;

public class Student {
  private int id;
  private String name;
  private String java;
  private String linux;
  private String data_structure;
  private String linear_algebra;
  private double grade;
  private boolean scholarship;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getJava() {
    return java;
  }
  public void setJava(String java) {
    this.java = java;
  }
  public String getLinux() {
    return linux;
  }
  public void setLinux(String linux) {
    this.linux = linux;
  }
  public String getData_structure() {
    return data_structure;
  }
  public void setData_structure(String data_structure) {
    this.data_structure = data_structure;
  }
  public String getLinear_algebra() {
    return linear_algebra;
  }
  public void setLinear_algebra(String linear_algebra) {
    this.linear_algebra = linear_algebra;
  }
  public double getGrade() {
    return grade;
  }
  public void setGrade(double grade) {
    this.grade = grade;
  }
  public boolean isScholarship() {
    return scholarship;
  }
  public void setScholarship(boolean scholarship) {
    this.scholarship = scholarship;
  }
}
