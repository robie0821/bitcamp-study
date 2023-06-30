package project1.util;

public class LinkedList implements List {
  Node head;
  Node tail;
  int size;

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

    if (head == tail) {
      head = null;
      tail = null;
    } else if (cursor == head) {
      head = cursor.next;
    } else if (cursor == tail) {
      tail = prev;
      tail.next = null;
    } else {
      prev.next = cursor.next;
    }

    cursor.next = null;
    cursor.value = null;
    size--;

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
