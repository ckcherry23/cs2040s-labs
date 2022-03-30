// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.ArrayList;

public class Dominoes {

  static boolean visited[];
  static int tpsort[];
  static int dominoCount;

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      int testCaseCount = Integer.parseInt(in.readLine());

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        while (testCaseCount > 0) {
          String counts[] = in.readLine().split(" ");
          int noOfDominoes = Integer.parseInt(counts[0]);
          int noOfCases = Integer.parseInt(counts[1]);

          visited = new boolean[noOfDominoes];
          tpsort = new int[noOfDominoes];

          dominoCount = noOfDominoes;

          ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>(
            noOfDominoes
          );
          for (int i = 0; i < noOfDominoes; i++) {
            adjacencyList.add(new ArrayList<Integer>());
          }
          for (int i = 0; i < noOfCases; i++) {
            String input[] = in.readLine().split(" ");
            int first = Integer.parseInt(input[0]) - 1;
            int second = Integer.parseInt(input[1]) - 1;

            adjacencyList.get(first).add(second);
          }

          int pushedDominoes = 0;

          for (int i = 0; i < noOfDominoes; i++) {
            if (!visited[i]) {
              toposort(i, adjacencyList);
            }
          }

          for (int i = 0; i < noOfDominoes; i++) {
            visited[i] = false;
          }

          for (int i = 0; i < noOfDominoes; i++) {
            if (!visited[tpsort[i]]) {
              pushedDominoes++;
              DFSrec(tpsort[i], adjacencyList);
            }
          }

          out.println(pushedDominoes);
          testCaseCount--;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void DFSrec(int u, ArrayList<ArrayList<Integer>> lst) {
    visited[u] = true;
    for (int i : lst.get(u)) {
      if (!visited[i]) {
        DFSrec(i, lst);
      }
    }
  }

  static void toposort(int u, ArrayList<ArrayList<Integer>> lst) {
    visited[u] = true;
    for (int i : lst.get(u)) {
      if (!visited[i]) {
        toposort(i, lst);
      }
    }
    tpsort[--dominoCount] = u;
  }
}
