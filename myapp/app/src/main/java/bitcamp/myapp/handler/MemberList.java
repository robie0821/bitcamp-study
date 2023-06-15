package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;

public class MemberList {
  private static final int MAX_SIZE = 3;
  private Member[] members = new Member[MAX_SIZE];
  private int length = 0;

  public void add(Member m) {
    if (!available()) {
      increase();
    }
    this.members[this.length++] = m;
  }

  private void increase( ) {
    Member[] arr = new Member[this.members.length + this.members.length/2];
    for(int i = 0; i < this.length; i++) {
      arr[i] = members[i];
    }
    members = arr;
    System.out.printf("배열 확장 : %d\n", this.members.length);
  }

  public Member[] list() {
    Member[] arr = new Member[this.length];
    for (int i = 0; i < this.length; i++) {
      arr[i] = this.members[i];
    }
    return arr;
  }

  public Member get(int no) {
    for(int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if(m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.members[i] = this.members[i + 1];
    }

    this.members[--this.length] = null;

    return true;
  }

  private int indexOf(int no) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == no) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return this.length < this.members.length;
  }
}
