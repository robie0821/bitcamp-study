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
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }
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
      System.out.printf("%3d. %s, %s, %s\n",
          no[i], name[i], email[i],
          toGenderString(gender[i]));
    }
  }

  public static void viewMember() {
    String memberNo = Prompt.inputString("번호? ");

    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", name[i]);
        System.out.printf("이메일: %s\n", email[i]);
        System.out.printf("성별: %s\n", toGenderString(gender[i]));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static void updateMember() {
    int memberNo = Integer.parseInt(Prompt.inputString("번호? "));

    for (int i = 0; i < length; i++) {
      if (no[i] == memberNo) {
        name[i] = Prompt.inputString("이름? ");
        email[i] = Prompt.inputString("이메일? ");
        password[i] = Prompt.inputString("암호? ");

        genLoop: while (true) {
          String gen = Prompt.inputString("성별: \n 1. 남자\n 2. 여자\n> ");

          switch (gen) {
            case "1":
              gender[i] = Male;
              break genLoop;
            case "2":
              gender[i] = Female;
              break genLoop;
            default:
              System.out.println("유효하지 않은 입력값");
          }
        }
      }
    }

  }

  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}