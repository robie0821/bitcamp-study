package project1;

public class App {
    public static void main(String[] args) {
      System.out.println("**대학교 학점 관리 시스템");
      System.out.println("----------------------------------");
      
      int no = 100;
      String name = "홍길동";
      String c = "A+";
      String java = "B";
      String linux = "A";
      String logic_design = "C+";
      String data_structure = "B";
      String linear_algebra = "C+";
      double grade = 3.34;
      boolean scholarship = true;


      System.out.printf("학번: %d\n", no);
      System.out.printf("이름: %s\n", name);
      System.out.printf("C언어: %s\n", c);
      System.out.printf("자바: %s\n", java);
      System.out.printf("리눅스: %s\n", linux);
      System.out.printf("논리 설계: %s\n", logic_design);
      System.out.printf("자료 구조: %s\n", data_structure);
      System.out.printf("선형대수학: %s\n", linear_algebra);
      System.out.printf("학점: %.2f\n", grade);
      System.out.printf("장학금: %b\n", scholarship);
    }
}
