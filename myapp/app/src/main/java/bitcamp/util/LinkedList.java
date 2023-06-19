package bitcamp.util;

public class LinkedList implements List {
  Node head;
  Node tail;
  int size;

  public static void main(String[] args) {
    LinkedList list = new LinkedList();

    list.add(Integer.valueOf(100));
    list.add(Integer.valueOf(200));
    list.add(Integer.valueOf(300));
    list.add(Integer.valueOf(400));
    list.add(Integer.valueOf(500));

    print(list);

    //    System.out.println(list.remove(Integer.valueOf(100)));
    //    System.out.println(list.remove(Integer.valueOf(400)));
    //    System.out.println(list.remove(Integer.valueOf(600)));

    System.out.println(list.remove(2));
    System.out.println(list.remove(3));
    System.out.println(list.remove(0));
    System.out.println(list.remove(0));
    System.out.println(list.remove(0));

    list.add(Integer.valueOf(1000));
    list.add(Integer.valueOf(2000));

    print(list);
  }

  static void print(LinkedList list) {
    Object[] arr = list.toArray();
    for(Object obj : arr) {
      System.out.print(obj);
      System.out.print(" ");
    }
    System.out.println();
  }

  @Override
  public boolean add(Object value) {
    Node node = new Node();
    node.value = value;

    if (head == null) {
      head = node;
    } else if (this.tail != null) {
      this.tail.next = node;
    }

    this.tail = node;
    this.size++;
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[size];

    Node node = this.head;

    for (int i = 0; i < this.size; i++) {
      arr[i] = node.value;
      node = node.next;
    }
    return arr;
  }

  @Override
  public Object get(int index) {
    if (!isValid(index)) {
      return null;
    }

    Node cursor = this.head;

    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }

    return cursor.value;
  }

  @Override
  public Object remove(int index) {
    if (!isValid(index)) {
      return null;
    }

    Node prev = null;
    Node cursor = this.head;

    for (int i = 0; i < index; i++) {
      prev = cursor;
      cursor = cursor.next;
    }

    Object old = cursor.value;
    if(prev == null) {
      head = cursor.next;
      if (head == null) {
        tail = null;
      }
    } else if (cursor.next == null) {
      tail = prev;
      tail.next = null;
    } else {
      prev.next = cursor.next;
    }
    size--;

    cursor.next = null;
    cursor.value = null;
    return old;
  }

  @Override
  public boolean remove(Object value) {
    Node prev = null;
    Node cursor = this.head;

    while (cursor != null) {
      if(cursor.value.equals(value)) {
        if(prev == null) {
          head = cursor.next;

          if (head == null) {
            tail = null;
          }

        } else if (cursor.next == null) {
          tail = prev;
          tail.next = null;
        } else {
          prev.next = cursor.next;
        }
        size--;

        cursor.next = null;
        cursor.value = null;

        return true;
      } else {
        prev = cursor;
        cursor = cursor.next;
      }
    }
    return false;
  }

  @Override
  public int size() {
    return this.size;
  }

  private boolean isValid(int index) {
    return index >= 0 && index < this.size;
  }

  static class Node {
    Object value;
    Node next;
  }
}