// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;

public class TequeLL {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      int noOfOps = Integer.parseInt(in.readLine());
      TLinkedList teque = new TLinkedList();

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        for (int i = 0; i < noOfOps; i++) {
          String[] input = in.readLine().split(" ");
          if (input[0].equals("push_back")) {
            teque.push_back(Long.parseLong(input[1]));
          } else if (input[0].equals("push_front")) {
            teque.push_front(Long.parseLong(input[1]));
          } else if (input[0].equals("push_middle")) {
            teque.push_middle(Long.parseLong(input[1]));
          } else if (input[0].equals("get")) {
            out.println(teque.get(Integer.parseInt(input[1])));
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class TLinkedList {

  Node head;
  Node tail;
  Node middle;
  int size;

  TLinkedList() {
    size = 0;
  }

  void push_back(long value) {
    Node temp = tail;
    tail = new Node(value, null, temp);
    if (size == 0) {
      head = tail;
      middle = tail;
      size++;
      return;
    }
    temp.next = tail;
    // Node current = middle;
    // while (current.next != null) {
    //   current = current.next;
    // }
    // current.next = tail;
    size++;
    if (size % 2 == 1) {
      middle = middle.next;
    }
    // System.out.printf("middle: %d\n", middle.value);
  }

  void push_front(long value) {
    head = new Node(value, head, null);
    if (size == 0) {
      tail = head;
      middle = head;
      size++;
      return;
    }
    Node current = head;
    current.next.prev = head;
    size++;
    middle = middle.prev;
    // System.out.printf("middle: %d\n", middle.value);
  }

  void push_middle(long value) {
    if (size == 0) {
      Node current = new Node(value, tail, head);
      head = current;
      tail = current;
      middle = current;
      size++;
      return;
    } else if (size == 1) {
      tail = new Node(value, null, head);
      head.next = tail;
      size++;
      return;
    }
    Node current = head;
    for (int i = 0; i < (int) (((size + 1) / 2) - 1); i++) {
      current = current.next;
    }
    Node temp = current.next;
    current.next = new Node(value, temp, current);
    temp.prev = current.next;
    size++;
    middle = current.next;
    // System.out.printf("middle: %d\n", middle.value);
    // Node temp = middle;
    // middle = new Node(value, temp, temp.next);
    // temp.next = middle;
    // middle.next.prev = middle;
  }

  long get(int index) {
    Node current;
    if (index < size / 3) {
      current = head;
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
    } else if (size >= 2 * size / 3) {
      current = tail;
      for (int i = 0; i < size - index - 1; i++) {
        current = current.prev;
      }
    } else {
      current = middle;
      if (index < size / 2) {
        for (int i = 0; i < (size / 2) - index - 1; i++) {
          current = current.prev;
        }
      } else {
        for (int i = 0; i < index - (size / 2) + 1; i++) {
          current = current.next;
        }
      }
    }
    return current.value;
  }
}

class Node {

  long value;
  Node next;
  Node prev;

  Node(long value, Node next, Node prev) {
    this.value = value;
    this.next = next;
    this.prev = prev;
  }

  @Override
  public String toString() {
    return Long.toString(value);
  }
}
