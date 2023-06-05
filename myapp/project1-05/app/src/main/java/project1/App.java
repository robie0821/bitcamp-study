package project1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
      System.out.println("**대학교 학점 관리 시스템");
      System.out.println("----------------------------------");

      Scanner sc = new Scanner(System.in);
      
      int size = 2;
      int[] no = new int[size];
      String[] name = new String[size];
      String[] c = new String[size];
      String[] java = new String[size];
      String[] linux = new String[size];
      String[] logic_design = new String[size];
      String[] data_structure = new String[size];
      String[] linear_algebra = new String[size];
      double[] grade = new double[size];
      boolean[] scholarship = new boolean[size];
      
      for (int i = 0; i < size; i++) {
        System.out.print("학번? ");
        no[i] = sc.nextInt();
        System.out.print("이름? ");
        name[i] = sc.next();
        System.out.print("C언어? ");
        c[i] = sc.next();
        System.out.print("자바? ");
        java[i] = sc.next();
        System.out.print("리눅스? ");
        linux[i] = sc.next();
        System.out.print("논리설계? ");
        logic_design[i] = sc.next();
        System.out.print("자료구조? ");
        data_structure[i] = sc.next();
        System.out.print("선형대수학? ");
        linear_algebra[i] = sc.next();
        System.out.print("학점? ");
        grade[i] = sc.nextFloat();
        System.out.print("장학금? ");
        scholarship[i] = sc.nextBoolean();
      }
      


      System.out.println("---------------------------------------");

      for (int i = 0; i < size; i++) {
        System.out.printf("학번: %d\n", no[i]);
        System.out.printf("이름: %s\n", name[i]);
        System.out.printf("C언어: %s\n", c[i]);
        System.out.printf("자바: %s\n", java[i]);
        System.out.printf("리눅스: %s\n", linux[i]);
        System.out.printf("논리 설계: %s\n", logic_design[i]);
        System.out.printf("자료 구조: %s\n", data_structure[i]);
        System.out.printf("선형대수학: %s\n", linear_algebra[i]);
        System.out.printf("학점: %.2f\n", grade[i]);
        System.out.printf("장학금: %b\n", scholarship[i]);
        System.out.println();
      }
      
    }
}
