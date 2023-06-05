package bitcamp.myapp;

import bitcamp.util.Calculator;

public class Test {

  static int result; // 스태틱변수는 0으로 초기화된다.

  public static void main(String[] args) {
    // 2 * 3 + 7 - 2 / 2
    // 연산자 우선순위를 고려하지않고앞에서부터 뒤로 순차적으로 계산한다.
    Calculator.init(2);
    Calculator.multiple(3);
    Calculator.plus(7);
    Calculator.minus(2);
    Calculator.divide(2);

    System.out.println(Calculator.result);
  }

}
