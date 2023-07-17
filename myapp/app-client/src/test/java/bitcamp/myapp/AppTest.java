package bitcamp.myapp;

import java.util.Arrays;
import java.util.Comparator;

class AppTest {
  public static void main(String[] args) {
    int[][] arr = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};

    Arrays.sort(arr, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        if (o1[0] == o2[0]) {
          return o1[1]-o2[1];
        } else {
          return o1[0]-o2[0];
        }
      }
    });

    for (int i = 0; i < arr.length; i++) {
      System.out.printf("[%d %d %d]\n", arr[i][0], arr[i][1], arr[i][2]);
    }

    return;
  }
}
