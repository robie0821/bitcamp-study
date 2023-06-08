package project1.system;

import project1.App;
import project1.prompt;
import project1.vo.Student;

import java.util.HashMap;

public class prime {

  public static int size = 10;
  static int length = 0;

  static Student[] student = new Student[size];

  static HashMap<Integer, Boolean> checkId = new HashMap<>();

  public static void generate() {
    while(true) {
      System.out.println("**대학교 학점 관리 시스템");
      System.out.println("----------------------------------");
      System.out.println("1. 학점 입력");
      System.out.println("2. 학점 출력");
      System.out.println("3. 학점 검색");
      System.out.println("4. 학점 수정");
      System.out.println("5. 학점 삭제");
      System.out.println("0. 종료");
      switch (prompt.inputString("> ")) {
        case "99":
          init();
          break;
        case "1":
          inputData();
          break;
        case "2":
          printData();
          break;
        case "3":
          searchData();
          break;
        case "4":
          updateData();
          break;
        case "5":
          deleteData();
          break;
        case "0":
          return;
        default:
          System.out.println("유효하지 않은 입력입니다");
          break;
      }
    }
  }

  public static void inputData() {
    while (available()) {
      Student std = new Student();
      std.id = inputId("학번? ");
      std.name = prompt.inputString("이름? ");
      std.java = inputScore("자바? ");
      std.linux = inputScore("리눅스? ");
      std.data_structure = inputScore("자료구조? ");
      std.linear_algebra = inputScore("선형대수학? ");
      std.grade = gradeCal(length, std);
      std.scholarship = scholarCal(std.grade);

      student[length++] = std;

      if(!promptContinue()) {
        break;
      }
    }
  }

  public static int inputId(String title) {
    while(true) {
      int temp = prompt.inputInt(title);
      if (checkId.containsKey(temp)) {
        System.out.println("중복된 학번입니다.");
      } else {
        checkId.put(temp, true);
        return temp;
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
        System.out.println("유효하지 않은 입력입니다");
          break;
      }
    }
  }

  static double gradeCal(int i, Student std) {
    String[] score = {std.java, std.linux, std.data_structure, std.linear_algebra};
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

  static boolean available() {
    if(length<size) {
      return true;
    } else {
      System.out.println("더이상 입력받을수 없습니다");
      System.out.println("계속하려면 아무키나 누르세요");
      prompt.sc.nextLine();
      return false;
    }
  }

  static boolean promptContinue() {
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
          System.out.println("유효하지 않은 입력입니다");
        }
      }
    }
  }

  public static void printData() {

    if (length == 0) {
      System.out.println("입력된 성적이 없습니다");
    } else {
      System.out.println("------------------------------------------------------------");
      System.out.println("학번, 이름, 자바, 리눅스, 자료구조, 선형대수학, 학점, 장학금");
      System.out.println("------------------------------------------------------------");
  
      for (int i = 0; i < length; i++) {
        Student std = student[i];
        System.out.printf("%2d. %4s, %4s, %6s, %8s, %10s, %4.2f, %6b\n", 
        std.id, std.name, std.java, std.linux, std.data_structure, std.linear_algebra,
        std.grade, std.scholarship);
      }
    }
    System.out.println("계속하려면 아무키나 누르세요");
    prompt.sc.nextLine();
    return;
  }
  
  public static void searchData() {
    while (true) {
      int index = indexOf();
      if(index == -1) {
        return;
      }
      Student std = student[index];
      System.out.println("------------------------------------------------------------");
      System.out.println("학번, 이름, 자바, 리눅스, 자료구조, 선형대수학, 학점, 장학금");
      System.out.println("------------------------------------------------------------");
      System.out.printf("%2d. %4s, %4s, %6s, %8s, %10s, %4.2f, %6b\n", 
      std.id, std.name, std.java, std.linux, std.data_structure,
      std.linear_algebra,std.grade, std.scholarship);
      
      if (!promptContinue()) {
        return;
      }
    }
  }

  public static void updateData() {
    while (true) {
      int index = indexOf();
      if(index == -1) {
        return;
      }
      Student std = student[index];
      checkId.remove(std.id);
      std.id = inputId("학번(" + std.id + ")? ");
      std.name = prompt.inputString("이름(" + std.name + ")? ");
      std.java = inputScore("자바(" + std.java + ")? ");
      std.linux = inputScore("리눅스(" + std.linux + ")? ");
      std.data_structure = inputScore("자료구조(" + std.data_structure + ")? ");
      std.linear_algebra = inputScore("선형대수학(" + std.linear_algebra + ")? ");
      std.grade = gradeCal(index, std);
      std.scholarship = scholarCal(std.grade);
      
      if (!promptContinue()) {
        return;
      }
    }
  }

  public static void deleteData() {
    while (true) {
      int index = indexOf();
      if(index == -1) {
        return;
      }
      for (int i = index; i < length - 1; i++) {
        student[i] = student[i + 1];
      }

      student[length - 1] = null;

      length--;

      System.out.println("삭제완료");

      if (!promptContinue()) {
        return;
      }
    }
  }

  public static int indexOf() {
    while (true) {
      switch (prompt.inputString("1. 학번으로 찾기\n2. 이름으로 찾기\n0. 종료\n>")) {
        case "1":
          int indexNum = prompt.inputInt("학번 :");
          for (int i = 0; i < length; i++) {
            Student std = student[i];
            if(std.id == indexNum) {
              return i;
            }
          }
          System.out.println("입력된 학번이 존재하지 않습니다");
          if (!promptContinue()) {
            return -1;
          }
          break;
        
        case "2":
          String indexName = prompt.inputString("이름 :");
          for (int i = 0; i < length; i++) {
            Student std = student[i];
            if(indexName.equals(std.name)) {
              return i;
            }
          }
          System.out.println("입력된 이름의 학생이 존재하지 않습니다");
          if (!promptContinue()) {
            return -1;
          }
          break;
        
        case "0":
          return -1;

        default:
          System.out.println("유효하지 않은 입력값입니다");
          break;
      }
    }
  }

  public static void init() {
    String[] SCORE = {"A", "B", "F"};
    String[] NAME = {"Kim", "Lee", "Park"};

    for(int i = 0; i < 3; i++) {
      Student std = new Student();
      std.id = 1001 + length;
      checkId.put(std.id, true);
      std.name = NAME[i];
      std.java = SCORE[i];
      std.linux = SCORE[i];
      std.data_structure = SCORE[i];  
      std.linear_algebra = SCORE[i];
      std.grade = gradeCal(i, std);
      std.scholarship = scholarCal(std.grade);
      student[length++] = std;
    }
  }
}
