package project1.vo;

public class User {
  private String Id;
  private String name;
  private String password;
  private int idNum;
  private Account account;

  public User() {
    this.account = new Account();
  }

  public User(int idNum) {
    this.idNum = idNum;
  }

  public String getId() {
    return Id;
  }
  public void setId(String id) {
    Id = id;
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
  public int getIdNum() {
    return idNum;
  }
  public void setIdNum(int idNum) {
    this.idNum = idNum;
  }
}

