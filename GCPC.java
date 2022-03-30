// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;

public class GCPC {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      String counts[] = in.readLine().split(" ");

      int noOfTeams = Integer.parseInt(counts[0]);
      int noOfEvents = Integer.parseInt(counts[1]);

      AVL scoreboard = new AVL(); 
      Team teamsList[] = new Team[noOfTeams];

      for (int i = 0; i < noOfTeams; i++) {
        teamsList[i] = new Team(i);
        scoreboard.insert(teamsList[i]);
      }

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {

        for (int i = 0; i < noOfEvents; i++) {
          String input[] = in.readLine().split(" ");
          int teamNo = Integer.parseInt(input[0]) - 1;
          int penalty = Integer.parseInt(input[1]);

          scoreboard.delete(teamsList[teamNo]);
          teamsList[teamNo].incrementWins();
          teamsList[teamNo].addPenalty(penalty);
          scoreboard.insert(teamsList[teamNo]); 
  
          out.println(scoreboard.rank(teamsList[0]));
        }
        
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class Team implements Comparable<Team> {
  int teamNo;
  int noOfWins;
  int penalty;

  static final Team empty = new Team(-1);

  Team(int teamNo) {
    this.teamNo = teamNo;
    this.noOfWins = 0;
    this.penalty = 0;
  }

  void incrementWins() {
    this.noOfWins++;
  }

  void addPenalty(int b) {
    this.penalty += b;
  }

  @Override
  public int compareTo(Team o) {
    if (this.noOfWins > o.noOfWins) {
      return -1;
    } else if (this.noOfWins == o.noOfWins && this.penalty < o.penalty) {
      return -1;
    } else if (this.noOfWins == o.noOfWins && this.penalty == o.penalty) {
      if (this.teamNo < o.teamNo) {
        return -1;
      } else if (this.teamNo > o.teamNo) {
        return 1;
      } else {
        return 0;
      }
    } else {
      return 1;
    }
  }

  @Override
  public String toString() {
      return Integer.toString(this.teamNo);
  }
}

class AVLNode {

  AVLNode(Team v) {
    key = v;

    parent = null;
    left = null;
    right = null;

    height = 0;
    size = 1;
  }

  AVLNode parent;
  AVLNode left;
  AVLNode right;
  Team key;
  int height;
  int size;
}

class AVL {

  AVLNode root;

  AVL() {
    this.root = null;
  }

  Team search(Team v) {
    AVLNode res = search(this.root, v);
    return res == null ? null : res.key;
  }

  private AVLNode search(AVLNode T, Team v) {
    if (T == null) {
      return null; 
    } else if (T.key.compareTo(v) == 0) {
      return T; 
    } else if (T.key.compareTo(v) == -1) {
      return search(T.right, v); 
    } else {
      return search(T.left, v);
    }
  }

  Team findMin() {
    return findMin(this.root);
  }

  private Team findMin(AVLNode T) {
    if (T.left == null) {
      return T.key;
    } else {
      return findMin(T.left);
    }
  }

  Team findMax() {
    return findMax(this.root);
  }

  private Team findMax(AVLNode T) {
    if (T.right == null) {
      return T.key;
    } else {
      return findMax(T.right);
    }
  }

  Team successor(Team v) {
    AVLNode curr = search(this.root, v);
    return curr == null ? null : successor(curr);
  }

  private Team successor(AVLNode T) {
    if (T.right != null) {
      return findMin(T.right);
    } else {
      AVLNode parent = T.parent;
      AVLNode curr = T;
      while ((parent != null) && (curr.equals(parent.right))) {
        curr = parent;
        parent = curr.parent;
      }
      return parent == null ? null : parent.key;
    }
  }

  Team predecessor(Team v) {
    AVLNode curr = search(this.root, v);
    return curr == null ? null : predecessor(curr);
  }

  private Team predecessor(AVLNode T) {
    if (T.left != null) {
      return findMax(T.left);
    } else {
      AVLNode parent = T.parent;
      AVLNode curr = T;

      while ((parent != null) && (curr.equals(parent.left))) {
        curr = parent;
        parent = curr.parent;
      }
      return parent == null ? null : parent.key;
    }
  }

  void inorder() {
    inorder(this.root);
    System.out.println();
  }

  private void inorder(AVLNode T) {
    if (T == null) {
      return;
    }

    inorder(T.left);
    System.out.printf("%d ", T.key);
    inorder(T.right);
  }

  void insert(Team v) {
    this.root = insert(this.root, v);
  }

  private AVLNode insert(AVLNode T, Team v) {
    if (T == null) {
      return new AVLNode(v);
    }

    if (T.key.compareTo(v) == -1) {
      T.right = insert(T.right, v);
      T.right.parent = T;
    } else {
      T.left = insert(T.left, v);
      T.left.parent = T;
    }

    return rebalance(T);
  }

  void delete(Team v) {
    this.root = delete(this.root, v);
  }

  private AVLNode delete(AVLNode T, Team v) {
    if (T == null) {
      return T;
    }

    if (T.key.compareTo(v) == -1) {
      T.right = delete(T.right, v);
    } else if (T.key.compareTo(v) == 1) {
      T.left = delete(T.left, v);
    } else {
      if (T.left == null && T.right == null) {
        T = null;
      } else if (T.left == null && T.right != null) {
        T.right.parent = T.parent;
        T = T.right;
      } else if (T.left != null && T.right == null) {
        T.left.parent = T.parent;
        T = T.left;
      } else {
        Team next = successor(v);
        T.key = next;
        T.right = delete(T.right, next);
      }
    }

    if (T != null) {
      T = rebalance(T);
    }

    return T;
  }

  void updateHeight(AVLNode T) {
    int leftHeight, rightHeight;
    if (T.left == null) {
      leftHeight = -1;
    } else {
      leftHeight = T.left.height;
    }

    if (T.right == null) {
      rightHeight = -1;
    } else {
      rightHeight = T.right.height;
    }
    T.height = Math.max(leftHeight, rightHeight) + 1;
  }

  void updateSize(AVLNode T) {
    if (T.left == null & T.right == null) {
      T.size = 1;
    } else if (T.left == null) {
      T.size = T.right.size + 1;
    } else if (T.right == null) {
      T.size = T.left.size + 1;
    } else {
      T.size = T.left.size + T.right.size + 1;
    }
  }

  int getBalanceFactor(AVLNode T) {
    if (T == null) {
      return 0;
    }
    int leftHeight, rightHeight;
    if (T.left == null) {
      leftHeight = -1;
    } else {
      leftHeight = T.left.height;
    }

    if (T.right == null) {
      rightHeight = -1;
    } else {
      rightHeight = T.right.height;
    }

    return leftHeight - rightHeight;
  }

  AVLNode rotateLeft(AVLNode T) {
    if (T.right != null) {
      AVLNode w = T.right;
      w.parent = T.parent;
      if (T.parent == null) {
        root = w;
      }
      T.parent = w;
      T.right = w.left;
      if (w.left != null) {
        w.left.parent = T; 
      }
      w.left = T;
      updateHeight(T);
      updateHeight(w);
      updateSize(T);
      updateSize(w);
      return w;
    }
    return null;
  }

  AVLNode rotateRight(AVLNode T) {
    if (T.left != null) {
      AVLNode w = T.left;
      w.parent = T.parent;
      if (T.parent == null) {
        root = w;
      }
      T.parent = w;
      T.left = w.right;
      if (w.right != null) {
        w.right.parent = T;
      }
      w.right = T;
      updateHeight(T);
      updateHeight(w);
      updateSize(T);
      updateSize(w);
      return w;
    }
    return null;
  }

  AVLNode rebalance(AVLNode T) {
    updateHeight(T);
    updateSize(T);
    if (
      getBalanceFactor(T) == 2 &&
      getBalanceFactor(T.left) <= 1 &&
      getBalanceFactor(T.left) >= 0
    ) {
      T = rotateRight(T);
    } else if (getBalanceFactor(T) == 2 && getBalanceFactor(T.left) == -1) {
      T.left = rotateLeft(T.left);
      T = rotateRight(T);
    } else if (
      getBalanceFactor(T) == -2 &&
      getBalanceFactor(T.right) <= 0 &&
      getBalanceFactor(T.right) >= -1
    ) {
      T = rotateLeft(T);
    } else if (getBalanceFactor(T) == -2 && getBalanceFactor(T.right) == 1) {
      T.right = rotateRight(T.right);
      T = rotateLeft(T);
    }
    return T;
  }

  int rank(Team v) {
    return rank(this.root, v);
  }

  private int rank(AVLNode T, Team v) {
    if (v.compareTo(T.key) == 0) {
      if (T.left == null) {
        return 1;
      }
      return T.left.size + 1;
    } else if (v.compareTo(T.key) == -1) {
      return rank(T.left, v);
    } else {
      if (T.left == null) {
        return rank(T.right, v) + 1;
      }   
      return rank(T.right, v) + T.left.size + 1;
    }
  }
}
