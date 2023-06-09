package bitcamp.test.step15.vo;

public class Score {
  private String name;
  int kor;
  int eng;
  int math;
  private int sum;
  private float aver;

  public Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
  }

  void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }

  public String getName() {
    return this.name;
  }

  public int getSum() {
    return this.sum;
  }

  public float getAver() {
    return this.aver;
  }
}
