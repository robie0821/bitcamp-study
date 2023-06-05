package project1.io;

import project1.App;
import project1.prompt;

public class io {

  public static int size = 3;
  static int userId = 1001;
  static int length = 0;
  static int[] no = new int[size];
  static String[] name = new String[size];
  static String[] java = new String[size];
  static String[] linux = new String[size];
  static String[] data_structure = new String[size];
  static String[] linear_algebra = new String[size];
  static double[] grade = new double[size];
  static boolean[] scholarship = new boolean[size];

  public static void printTitle() {
    System.out.println("**대학교 학점 관리 시스템");
    System.out.println("----------------------------------");
  }

  public static void inputGrade(int i) {
    name[i] = prompt.inputString("이름? ");
    java[i] = prompt.inputString("자바? ");
    linux[i] = prompt.inputString("리눅스? ");
    data_structure[i] = prompt.inputString("자료구조? ");
    linear_algebra[i] = prompt.inputString("선형대수학? ");

    grade[i] = gradeCalculator(i);

    if(grade[i] >= 3.0) {
      scholarship[i] = true;
    } else {
      scholarship[i] = false;
    }

    no[i] = userId++;
    length++;

    prompt.sc.nextLine();
  }

  static double gradeCalculator(int i) {
    String[] score = {java[i], linux[i], data_structure[i], linear_algebra[i]};
    double sum = 0;

    for (int t = 0; t < 4; t++) {
      switch (score[t]) {
        case "A+":
          sum += 4.5;
          break;
        case "A":
          sum += 4.0;
          break;
        case "B+":
          sum += 3.5;
          break;
        case "B":
          sum += 3.0;
          break;
        case "C+":
          sum += 2.5;
          break;
        case "C":
          sum += 2.0;
          break;
        case "D+":
          sum += 1.5;
          break;
        case "D":
          sum += 1.0;
          break;
        case "F":
          sum += 0;
          break;
      }
    }
    return sum/4;
  }

  public static void printGrade() {
    System.out.println("------------------------------------------------------------");
    System.out.println("학번, 이름, 자바, 리눅스, 자료구조, 선형대수학, 학점, 장학금");
    System.out.println("------------------------------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%2d. %4s, %4s, %6s, %8s, %10s, %4.2f, %6b", no[i], name[i], java[i], linux[i], data_structure[i], linear_algebra[i], grade[i], scholarship[i]);
      System.out.println();
    }

  }
}
