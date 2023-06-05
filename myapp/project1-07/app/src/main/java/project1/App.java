package project1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
      System.out.println("**대학교 학점 관리 시스템");
      System.out.println("----------------------------------");

      Scanner sc = new Scanner(System.in);
      
      int size = 3;
      int userId = 1001;
      int length = 0;
      int[] no = new int[size];
      String[] name = new String[size];
      String[] java = new String[size];
      String[] linux = new String[size];
      String[] data_structure = new String[size];
      String[] linear_algebra = new String[size];
      double[] grade = new double[size];
      boolean[] scholarship = new boolean[size];

      
      
      
      for (int i = 0; i < size; i++) {
        
        inputGrade(sc, i, name, java, linux,
                   data_structure,  linear_algebra,
                   grade, scholarship);

        no[i] = userId++;
        length++;

        if (!promptContinue(sc)) {
          break;
        }

      }
      
      printGrade(length, no, name,
                 java, linux, data_structure, linear_algebra,
                 grade, scholarship);
      
      
    }

    static void inputGrade(Scanner sc, int i, String[] name, 
                           String[] java, String[] linux,
                           String[] data_structure, String[] linear_algebra, 
                           double[] grade, boolean[] scholarship) {
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

    static boolean promptContinue(Scanner sc) {
      System.out.print("계속 하시겠습니까?(Y/n) ");
      String response = sc.nextLine();
      if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
        return false;
      }
      return true;
    }

    static void printGrade(int length, int[] no, String[] name,
                           String[] java, String[] linux,
                           String[] data_structure, String[] linear_algebra,
                           double[] grade, boolean[] scholarship) {
      System.out.println("------------------------------------------------------------");
      System.out.println("학번, 이름, 자바, 리눅스, 자료구조, 선형대수학, 학점, 장학금");
      System.out.println("------------------------------------------------------------");

      for (int i = 0; i < length; i++) {
        System.out.printf("%2d. %4s, %4s, %6s, %8s, %10s, %4.2f, %6b", no[i], name[i], java[i], linux[i], data_structure[i], linear_algebra[i], grade[i], scholarship[i]);
        System.out.println();
      }

                           }
}
