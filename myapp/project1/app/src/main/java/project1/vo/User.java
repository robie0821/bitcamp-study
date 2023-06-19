package project1.vo;

public class User {
  private String Id;
  private String name;
  private String password;
  private String accNum;
  private int balance;
  private Account account;

  public User() {
    this.account = new Account();
  }

  public User(String accNum) {
    this.account = new Account();
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
  public String getAccNum() {
    return this.accNum;
  }
  public void setAccNum(String accNum) {
    this.accNum = accNum;
  }
  public int getBalance() {
    return balance;
  }
  public void setBalance(int balance) {
    this.balance = balance;
  }
}

