package project1.vo;

public class Account {
  private static int length = 0;
  private String accNum;
  private int balance;

  public class history {
    private int amount;
    private boolean state;
    private long depositDate;

    public history() {

    }

    public int getAmount() {
      return amount;
    }
    public void setAmount(int amount) {
      this.amount = amount;
    }
    public boolean isState() {
      return state;
    }
    public void setState(boolean state) {
      this.state = state;
    }
    public long getDepositDate() {
      return depositDate;
    }
  }



  public Account() {

  }
}
