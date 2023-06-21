package bitcamp.util;

public class ArrayList implements List {
  private static final int DEFAULT_SIZE = 3;

  private Object[] list = new Object[DEFAULT_SIZE];
  private int length;

  @Override
  // 컴파일러에게 다음 메서드가 수퍼클래스의 메서드를 재정의한 것인지?
  // 또는 인터페이스의 메서드를 구현한 것인지?
  // 검사해달라는 표시다.
  public boolean add(Object obj) {
    if (length == list.length) {
      increase();
    }
    list[length++] = obj;
    return true;
  }

  private void increase() {
    Object[] arr = new Object[list.length + (list.length >> 1)];
    for (int i = 0; i < list.length; i++) {
      arr[i] = list[i];
    }
    list = arr;
    //System.out.println("배열 확장: " + list.length);
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[length];
    for (int i = 0; i < length; i++) {
      arr[i] = list[i];
    }
    return arr;
  }

  @Override
  public Object get(int index) {
    if (!isValid(index)) {
      return null;
    }
    return list[index];
  }

  @Override
  public boolean remove(Object obj) {
    int deletedIndex = indexOf(obj);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      list[i] = list[i + 1];
    }
    list[--length] = null;
    return true;
  }

  @Override
  public Object remove(int index) {
    if (!isValid(index)) {
      return null;
    }

    Object old = list[index];

    for (int i = index; i < length - 1; i++) {
      list[i] = list[i + 1];
    }
    list[--length] = null;

    return old;
  }

  @Override
  public int size() {
    return length;
  }

  private boolean isValid(int index) {
    return index >= 0 && index < length;
  }

  private int indexOf(Object obj) {
    for (int i = 0; i < length; i++) {
      Object item = list[i];
      if (item.equals(obj)) {
        return i;
      }
    }
    return -1;
  }
}
