package bitcamp.myapp.handler;

import bitcamp.util.Prompt;

public class MemberHandler {

  public static final int MAX_SIZE = 3;
  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] email = new String[MAX_SIZE];
  static String[] password = new String[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];
  public static int length = 0;
  static int userId = 1;
  static final char Male = 'M';
  static final char Female = 'W';

  public static void inputMember() {
    name[length] = Prompt.inputString("이름? ");
    email[length] = Prompt.inputString("이메일? ");
    password[length] = Prompt.inputString("암호? ");

    genLoop: while (true) {
      String menuNO = Prompt.inputString("성별: \n 1. 남자\n 2. 여자\n> ");

      switch (menuNO) {
        case "1":
          gender[length] = Male;
          break genLoop;
        case "2":
          gender[length] = Female;
          break genLoop;
        default:
          System.out.println("유효하지 않은 입력값");
      }
    }
    no[length] = userId++;
    length++;
  }

  public static void printMembers() {

    System.out.println("------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%3d. %s, %s, %c\n", no[i], name[i], email[i], gender[i]);
      if (i != length - 1) {
        System.out.println();
      }
    }
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}
