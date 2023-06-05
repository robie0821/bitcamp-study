package project1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
      System.out.println("**대학교 학점 관리 시스템");
      System.out.println("----------------------------------");

      Scanner sc = new Scanner(System.in);
      
      System.out.print("학번? ");
      int no = sc.nextInt();

      System.out.print("이름? ");
      String name = sc.next();

      System.out.print("C언어? ");
      String c = sc.next();

      System.out.print("자바? ");
      String java = sc.next();

      System.out.print("리눅스? ");
      String linux = sc.next();

      System.out.print("논리설계? ");
      String logic_design = sc.next();

      System.out.print("자료구조? ");
      String data_structure = sc.next();

      System.out.print("선형대수학? ");
      String linear_algebra = sc.next();

      double grade = 3.34;
      boolean scholarship = true;

      System.out.println("---------------------------------------");

      System.out.printf("학번: %d\n", no);
      System.out.printf("이름: %s\n", name);
      System.out.printf("C언어: %s\n", c);
      System.out.printf("자바: %s\n", java);
      System.out.printf("리눅스: %s\n", linux);
      System.out.printf("논리 설계: %s\n", logic_design);
      System.out.printf("자료 구조: %s\n", data_structure);
      System.out.printf("선형대수학: %s\n", linear_algebra);
      // System.out.printf("학점: %.2f\n", grade);
      // System.out.printf("장학금: %b\n", scholarship);
    }
}
