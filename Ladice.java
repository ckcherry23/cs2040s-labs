// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;

public class Ladice {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      String counts[] = in.readLine().split(" ");

      int noOfItems = Integer.parseInt(counts[0]);
      int noOfDrawers = Integer.parseInt(counts[1]);

      UFDS drawers = new UFDS(noOfDrawers);

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        for (int i = 0; i < noOfItems; i++) {
          String input[] = in.readLine().split(" ");

          int a = Integer.parseInt(input[0]);
          int b = Integer.parseInt(input[1]);

          drawers.unionSet(a - 1, b - 1);
          int set = drawers.findSet(a - 1);

          if (drawers.noOfOccupied[set] < drawers.capacity[set]) {
            out.println("LADICA"); // drawer
            drawers.noOfOccupied[set]++;
          } else {
            out.println("SMECE"); // trash
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class UFDS {

  public int[] p;
  public int[] rank;

  public int[] capacity;
  public int[] noOfOccupied;

  public UFDS(int N) {
    p = new int[N];
    rank = new int[N];
    
    capacity = new int[N];
    noOfOccupied = new int[N];

    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
      capacity[i] = 1;
      noOfOccupied[i] = 0;
    }
  }

  public int findSet(int i) {
    if (p[i] == i) {
      return i;
    } else {
      p[i] = findSet(p[i]);
      return p[i];
    }
  }

  public Boolean isSameSet(int i, int j) {
    return findSet(i) == findSet(j);
  }

  public void unionSet(int i, int j) {
    if (!isSameSet(i, j)) {
      int x = findSet(i);
      int y = findSet(j);

      if (rank[x] > rank[y]) {
        p[y] = x;
      } else {
        p[x] = y;
        if (rank[x] == rank[y]) {
          rank[y]++;
        }
      }

      capacity[x] += capacity[y];
      capacity[y] = capacity[x];

      noOfOccupied[x] += noOfOccupied[y];
      noOfOccupied[y] = noOfOccupied[x];
    }
  }
}
