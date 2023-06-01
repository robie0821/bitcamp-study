package bitcamp.myapp;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    final int MAX_SIZE = 3;
    int length = 0;
    int userId = 1;

    int[] no = new int[MAX_SIZE];
    String[] name = new String[MAX_SIZE];
    String[] email = new String[MAX_SIZE];
    String[] password = new String[MAX_SIZE];
    char[] gender = new char[MAX_SIZE];

    printTitle();

    for (int i = 0; i < MAX_SIZE; i++) {

      inputMember(sc, i, no, name, email, password, gender, userId++);

      length++;

      sc.nextLine();
      if (i < MAX_SIZE - 1) {
        if (promptContinue(sc)) {
          System.out.println();
        } else {
          break;
        }
      }
    }

    printMembers(length, no, name, email, gender);

    sc.close();
  }

  // --------------------------------------------------
  // --------------------------------------------------
  // --------------------------------------------------

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("------------------------------");
  }

  static void inputMember(Scanner sc, int i, int[] no, String[] name,
      String[] email, String[] password, char[] gender, int userId) {
    System.out.printf("이름? ");
    name[i] = sc.next();

    System.out.printf("이메일? ");
    email[i] = sc.next();

    System.out.printf("암호? ");
    password[i] = sc.next();

    genLoop: while (true) {
      System.out.printf("성별: \n 1. 남자\n 2. 여자\n> ");
      String menuNO = sc.next();

      switch (menuNO) {
        case "1":
          gender[i] = 'M';
          break genLoop;
        case "2":
          gender[i] = 'W';
          break genLoop;
        default:
          System.out.println("유효하지 않은 입력값");
      }
    }
    no[i] = userId;
  }

  static boolean promptContinue(Scanner sc) {
    while (true) {
      System.out.print("계속 하시겠습니까?(Y/n) ");
      String str = sc.nextLine();
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

  static void printMembers(int length, int[] no,
      String[] name, String[] email, char[] gender) {

    System.out.println("------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%3d. %s, %s, %c\n", no[i], name[i], email[i], gender[i]);
      System.out.println();
    }
  }
}
