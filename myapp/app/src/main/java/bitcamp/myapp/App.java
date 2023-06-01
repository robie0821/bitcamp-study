package bitcamp.myapp;

import java.util.*;

public class App {
  public static void main(String[] args) {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------------");

    // 키보드 스캐너 준비
    Scanner sc = new Scanner(System.in);

    int[] no = new int[3];
    String[] name = new String[3];
    int[] age = new int[3];
    boolean[] working = new boolean[3];
    char[] gender = new char[3];
    float[] leftEye = new float[3];
    float[] rightEye = new float[3];

    for (int i = 0; i < 3; i++) {
      System.out.printf("번호? ");
      no[i] = sc.nextInt();

      System.out.printf("이름? ");
      name[i] = sc.next();

      System.out.printf("나이? ");
      age[i] = sc.nextInt();

      System.out.printf("재직중(true/false)? ");
      working[i] = sc.nextBoolean();

      System.out.printf("성별(남자:M, 여자:W)? ");
      gender[i] = sc.next().charAt(0);

      System.out.printf("시력(왼쪽, 오른쪽)? ");
      leftEye[i] = sc.nextFloat();
      rightEye[i] = sc.nextFloat();
    }

    System.out.println("--------------------------------------------------");

    for (int i = 0; i < 3; i++) {
      System.out.printf("번호: %d\n", no[i]);
      System.out.printf("이름: %s\n", name[i]);
      System.out.printf("나이: %d\n", age[i]);
      System.out.printf("재직자: %b\n", working[i]);
      System.out.printf("성별(남자(M), 여자(W)): %c\n", gender[i]);
      System.out.printf("좌우시력: %.1f, %.1f\n", leftEye[i], rightEye[i]);
      System.out.println();
    }

    sc.close();
  }
}
