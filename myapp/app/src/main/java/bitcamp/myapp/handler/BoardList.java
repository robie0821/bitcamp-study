package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;

public class BoardList {
  private static final int MAX_SIZE = 3;
  private Board[] boards = new Board[MAX_SIZE];
  private int length = 0;

  public void add(Board board) {
    if (!this.available()) {
      increase();
    }
    this.boards[this.length++] = board;
  }

  private void increase() {
    Board[] arr = new Board[this.boards.length + this.boards.length/2];
    for(int i = 0 ; i < length; i++) {
      arr[i] = this.boards[i];
    }
    boards = arr;
    System.out.printf("배열 확장 : %d\n", this.boards.length);
  }

  public Board[] list() {
    Board[] arr = new Board[this.length];
    for(int i = 0; i < length; i++) {
      arr[i] = boards[i];
    }
    return arr;
  }

  public Board get(int no) {
    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
      if (board.getNo() == no) {
        board.setViewCount(board.getViewCount() + 1);
        return board;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = this.indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.boards[i] = this.boards[i + 1];
    }

    this.boards[--this.length] = null;
    return true;
  }

  private int indexOf(int no) {
    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
      if (board.getNo() == no) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return this.length < this.boards.length;
  }
}
