package project1;

import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    static int size = 3;
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

    public static void main(String[] args) {
      System.out.println("**대학교 학점 관리 시스템");
      System.out.println("----------------------------------");

      

      
      
      
      for (int i = 0; i < size; i++) {
        
        inputGrade(i);

        no[i] = userId++;
        length++;

        if (!promptContinue()) {
          break;
        }

      }
      
      printGrade();
      
      
    }

    static void inputGrade(int i) {
      System.out.print("이름? ");
      name[i] = sc.next();
      System.out.print("자바? ");
      java[i] = sc.next();
      System.out.print("리눅스? ");
      linux[i] = sc.next();
      System.out.print("자료구조? ");
      data_structure[i] = sc.next();
      System.out.print("선형대수학? ");
      linear_algebra[i] = sc.next();
      System.out.print("학점? ");
      grade[i] = sc.nextFloat();

      if(grade[i] >= 3.0) {
        scholarship[i] = true;
      } else {
        scholarship[i] = false;
      }

      sc.nextLine();
    }

    static boolean promptContinue() {
      System.out.print("계속 하시겠습니까?(Y/n) ");
      String response = sc.nextLine();
      if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
        return false;
      }
      return true;
    }

    static void printGrade() {
      System.out.println("------------------------------------------------------------");
      System.out.println("학번, 이름, 자바, 리눅스, 자료구조, 선형대수학, 학점, 장학금");
      System.out.println("------------------------------------------------------------");

      for (int i = 0; i < length; i++) {
        System.out.printf("%2d. %4s, %4s, %6s, %8s, %10s, %4.2f, %6b", no[i], name[i], java[i], linux[i], data_structure[i], linear_algebra[i], grade[i], scholarship[i]);
        System.out.println();
      }

    }
}
