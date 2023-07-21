package com.eomcs.generic.ex02;


public class Exam0110 {

  static class Box<T> {
    void set(T obj) {}
  }

  public static void main(String[] args) {
    Box<String> box1 = new Box<>();
    Box<Integer> box2 = new Box<>();
    Box<Member> box3 = new Box<>();

    //    box1 = new Box<Object>(); // 컴파일 오류
    //    box2 = new Box<String>(); // 컴파일 오류
    //    box3 = new Box<Object>(); // 컴파일 오류
  }
}








