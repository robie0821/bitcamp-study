package project.myapp.vo;

import java.io.Serializable;

public class Student implements Serializable, CsvObject{
  private static final long serialVersionUID = 1L;
  
  private int no;
  private String name;
  private String password;
  private int[] subject;
  private String[] score;
  private double grade;
  private boolean scholar;
  
  public static Member fromCsv(String csv) {
    String[] values = csv.split(",");

    Member member = new Member(Integer.parseInt(values[0]));
    member.setName(values[1]);
    member.setEmail(values[2]);
    member.setPassword(values[3]);
    member.setGender(values[4].charAt(0));

    if (Member.userId <= member.getNo()) {
      Member.userId = member.getNo() + 1;
    }

    return member;
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%c",
        this.getNo(),
        this.getName(),
        this.getEmail(),
        this.getPassword(),
        this.getGender());
  }
  
}
