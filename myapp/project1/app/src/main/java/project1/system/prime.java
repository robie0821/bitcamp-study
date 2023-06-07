package project1.system;

import project1.App;
import project1.prompt;
import java.util.HashMap;

public class prime {

  public static int size = 3;
  static int userId = 1001;
  static int length = 0;
  static int[] index = new int[size];
  static String[] name = new String[size];
  static String[] java = new String[size];
  static String[] linux = new String[size];
  static String[] data_structure = new String[size];
  static String[] linear_algebra = new String[size];
  static double[] grade = new double[size];
  static boolean[] scholarship = new boolean[size];

  static HashMap<String, Integer> map = new HashMap<>();

  public static void generate() {
    while(true) {
      System.out.println("**대학교 학점 관리 시스템");
      System.out.println("----------------------------------");
      System.out.println("1. 성적 입력");
      System.out.println("2. 성적 출력");
      System.out.println("3. 성적 검색");
      System.out.println("0. 종료");
      switch (prompt.inputString("> ")) {
        case "1":
          inputData();
          break;
        case "2":
          printData();
          break;
        case "3":
          searchData();
          break;
        case "0":
          return;
        default:
          System.out.println("잘못된 입력 번호입니다.");
          break;
      }
    }
  }

  public static void inputData() {
    while (true) {
      name[length] = prompt.inputString("이름? ");
      map.put(name[length], length);
      java[length] = inputScore("자바? ");
      linux[length] = inputScore("리눅스? ");
      data_structure[length] = inputScore("자료구조? ");
      linear_algebra[length] = inputScore("선형대수학? ");
  
      grade[length] = gradeCal(length);
      scholarship[length] = scholarCal(grade[length]);

      index[length] = userId++;
      length++;

      if(!promptContinue()) {
        break;
      }
    }
  }

  static String inputScore(String str) {
    while (true) {
      String score = prompt.inputString(str);
      switch (score) {
        case "A+", "A", "B+", "B", "C+", "C", "D+", "D", "F":
        case "a+", "a", "b+", "b", "c+", "c", "d+", "d", "f":
          score = score.toUpperCase();
          return score;
        default:
          System.out.println("잘못된 입력 입니다.");
          break;
      }
    }
  }

  static double gradeCal(int i) {
    String[] score = {java[i], linux[i], data_structure[i], linear_algebra[i]};
    double sum = 0;

    for (int t = 0; t < 4; t++) {
      switch (score[t]) {
        case "A+", "a+":
          sum += 4.5;
          break;
        case "A", "a":
          sum += 4.0;
          break;
        case "B+", "b+":
          sum += 3.5;
          break;
        case "B", "b":
          sum += 3.0;
          break;
        case "C+", "c+":
          sum += 2.5;
          break;
        case "C", "c":
          sum += 2.0;
          break;
        case "D+", "d+":
          sum += 1.5;
          break;
        case "D", "d":
          sum += 1.0;
          break;
        case "F", "f":
          sum += 0;
          break;
      }
    }
    return sum/4;
  }

  static boolean scholarCal(double grade) {
    if(grade >= 3.0) {
      return true;
    } else {
      return false;
    }
  }

  static boolean promptContinue() {
    prompt.sc.nextLine();
    while (true) {
      System.out.print("계속 하시겠습니까?(Y/n) ");
      String str = prompt.sc.nextLine();
      switch (str) {
        case "Y", "y", "": {
          return true;
        }
        case "N", "n": {
          return false;
        }
        default: {
          System.out.println("유효하지 않은 입력값");
        }
      }
    }
  }

  public static void printData() {

    if (length == 0) {
      System.out.println("입력된 성적이 없습니다.");
    } else {
      System.out.println("------------------------------------------------------------");
      System.out.println("학번, 이름, 자바, 리눅스, 자료구조, 선형대수학, 학점, 장학금");
      System.out.println("------------------------------------------------------------");
  
      for (int i = 0; i < length; i++) {
        System.out.printf("%2d. %4s, %4s, %6s, %8s, %10s, %4.2f, %6b\n", 
        index[i], name[i], java[i], linux[i], data_structure[i], linear_algebra[i], grade[i], scholarship[i]);
      }
    }
    System.out.println("계속하려면 아무키나 누르세요");
    prompt.sc.nextLine();
    prompt.sc.nextLine();
    return;
  }
  
  public static void searchData() {
    while (true) {
      String str = prompt.inputString("이름? ");
      if(map.containsKey(str)) {
        int i = map.get(str);
        System.out.println("------------------------------------------------------------");
        System.out.println("학번, 이름, 자바, 리눅스, 자료구조, 선형대수학, 학점, 장학금");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%2d. %4s, %4s, %6s, %8s, %10s, %4.2f, %6b\n", 
        index[i], name[i], java[i], linux[i], data_structure[i], linear_algebra[i], grade[i], scholarship[i]);
      } else {
        System.out.println("입력된 학생이 존재하지 않습니다.");
      }
      if (!promptContinue()) {
        return;
      }
    }
  }
  
}
