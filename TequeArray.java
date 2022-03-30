import java.io.*;
import java.util.Arrays;

public class TequeArray {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      int noOfOps = Integer.parseInt(in.readLine());
      Teque teque = new Teque();

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

// Modified version of L6 QueueArr.java
class Deque {
    
  final int MAXSIZE = 1000000;

  long[] queue;
  int front;
  int back;
  int size;

  Deque() {
    queue = new long[MAXSIZE];
    front = -1;
    back = -1;
    size = 0;
  }

  long removeFront() {
    long item = queue[front];
    front = (front + 1) % MAXSIZE;
    size--;
    return item;
  }

  long removeBack() {
    long item = queue[back];
    if (back == 0) {
      back = MAXSIZE - 1;
    } else {
      back--;
    }
    size--;
    return item;
  }

  void addFront(long value) {
    if (front == -1 && back == -1) {
      front++;
      back++;
    } else if (front == 0) {
      front = MAXSIZE - 1;
    } else {
      front--;
    }
    queue[front] = value;
    size++;
  }

  void addBack(long value) {
    if (front == -1 && back == -1) {
      front++;
    }
    back = (back + 1) % MAXSIZE;
    queue[back] = value;
    size++;
  }

  long get(int index) {
    return queue[(front + index) % MAXSIZE];
  }

  @Override
  public String toString() {
      return Arrays.toString(queue);
  }
}

class Teque {

  Deque leftHalf;
  Deque rightHalf;

  Teque() {
    leftHalf = new Deque();
    rightHalf = new Deque();
  }

  void push_back(long value) {
    rightHalf.addBack(value);
    shiftCenter();
  }

  void push_front(long value) {
    leftHalf.addFront(value);
    shiftCenter();
  }

  void push_middle(long value) {
    leftHalf.addBack(value);
    shiftCenter();
  }

  long get(int index) {
    if (index < size() / 2) {
      return leftHalf.get(index);
    } else if (index > size() / 2) {
      return rightHalf.get(index - leftHalf.size);
    } else {
        if (size() % 2 == 0) {
            return rightHalf.get(index - leftHalf.size);
        } else {
            return leftHalf.get(index);
        }
    }
  }

  int size() {
      return leftHalf.size + rightHalf.size;
  }

  void shiftCenter() {
    if (leftHalf.size > rightHalf.size) {
      if (leftHalf.size != rightHalf.size + 1) {
      rightHalf.addFront(leftHalf.removeBack());
        shiftCenter();
      }
    }
    else if (leftHalf.size < rightHalf.size) {
      leftHalf.addBack(rightHalf.removeFront());
      shiftCenter();
    }
  }

  @Override
  public String toString() {
      return leftHalf.toString() + rightHalf.toString();
  }
}
