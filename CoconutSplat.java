// Name: Charisma Kausar
// Student Number: A0226593X

import java.util.*;
import java.io.*;

public class CoconutSplat {
  public static final int FOLDED = 0;
  public static final int FIST = 1;
  public static final int PALM = 2;
  public static final int BEHIND = 3;

  public static final int BOTH = 4;
  public static final int RIGHT = 5;
  public static final int LEFT = 6;

  static int playersLeft = 0;
  static int current = -1;

  static void setCurrent(int num) {
    current = num;
  }

  static void incrementPlayers() {
    playersLeft++;
  }

  static void decrementPlayers() {
    playersLeft--;
  }

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      String[] line = in.readLine().split(" ");

      int noOfSyllables = Integer.parseInt(line[0]);
      int noOfPlayers = Integer.parseInt(line[1]);

      CircularLinkedList<Player> circle = new CircularLinkedList<Player>();

      playersLeft = noOfPlayers;

      for (int i = 0; i < noOfPlayers; i++) {
        Player p = new Player(i + 1, FOLDED, BOTH);
        circle.add(p);
      }

      while (playersLeft > 1) {
        Player.touchedLast(circle.get(noOfSyllables + current), circle, noOfSyllables + current);
      }

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        out.println(circle.getFirst().getId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class Player {
  public static final int FOLDED = 0;
  public static final int FIST = 1;
  public static final int PALM = 2;
  public static final int BEHIND = 3;

  public static final int BOTH = 4;
  public static final int RIGHT = 5;
  public static final int LEFT = 6;

  int id;
  int state;
  int handType;

  Player(int id, int state, int handType) {
    this.id = id;
    this.state = state;
    this.handType = handType;
  }

  int getId() {
    return id;
  }

  static void touchedLast(Player p, CircularLinkedList<Player> circle, int index) {
    if (p.state == FOLDED) {
      p.state = FIST;
      p.handType = RIGHT;
      Player newP = new Player(p.id, FIST, LEFT);
      circle.add(index + 1, newP);
      CoconutSplat.setCurrent(circle.indexOf(p) - 1);
      CoconutSplat.incrementPlayers();
    } else if (p.state == FIST) {
      p.state = PALM;
      CoconutSplat.setCurrent(circle.indexOf(p));
    } else if (p.state == PALM) {
      CoconutSplat.setCurrent(circle.indexOf(p) - 1);
      circle.remove(p);
      p.state = BEHIND;
      CoconutSplat.decrementPlayers();
    }
  }

  @Override
  public String toString() {
    return Integer.toString(id) + " " + state + " " + handType;
  }
}

class CircularLinkedList<T> extends LinkedList<T> {
  @Override
  public T get(int index) {
      if (index < this.size()) {
        return super.get(index);
      } else {
        return super.get(index % this.size());
      }
  }

  @Override
  public void add(int index, T element) {
    if (index == this.size()) {
      super.add(index, element);
    } else {
    super.add(index % this.size(), element);
    }
  }
}