// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.PriorityQueue;

public class MillionaireMadness {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      String counts[] = in.readLine().split(" ");
      int noOfRows = Integer.parseInt(counts[0]);
      int noOfColumns = Integer.parseInt(counts[1]);

      PriorityQueue<SP> PQ = new PriorityQueue<SP>();

      int stacks[][] = new int[noOfRows][noOfColumns];
      boolean isConnected[][] = new boolean[noOfRows][noOfColumns];

      for (int i = 0; i < noOfRows; i++) {
        String input[] = in.readLine().split(" ");
        for (int j = 0; j < noOfColumns; j++) {
          stacks[i][j] = Integer.parseInt(input[j]);
        }
      }

      PQ.add(new SP(stacks[0][0], 0, 0, 0));

      if (noOfRows > 1) {
        PQ.add(
          new SP(stacks[1][0], Math.max(stacks[1][0] - stacks[0][0], 0), 1, 0)
        );
      }
      if (noOfColumns > 1) {
        PQ.add(
          new SP(stacks[0][1], Math.max(stacks[0][1] - stacks[0][0], 0), 0, 1)
        );
      }

      int max = 0;

      while (!PQ.isEmpty()) {
        SP edge = PQ.poll();

        if (
          !isConnected[edge.row][edge.column] &&
          !isConnected[noOfRows - 1][noOfColumns - 1]
        ) {
          isConnected[edge.row][edge.column] = true;
          if (edge.shortestPath > max) {
            max = edge.shortestPath;
          }
          if (edge.row > 0) {
            PQ.add(
              new SP(
                stacks[edge.row - 1][edge.column],
                Math.max(
                  stacks[edge.row - 1][edge.column] -
                  stacks[edge.row][edge.column],
                  0
                ),
                edge.row - 1,
                edge.column
              )
            );
          }
          if (edge.row < noOfRows - 1) {
            PQ.add(
              new SP(
                stacks[edge.row + 1][edge.column],
                Math.max(
                  stacks[edge.row + 1][edge.column] -
                  stacks[edge.row][edge.column],
                  0
                ),
                edge.row + 1,
                edge.column
              )
            );
          }
          if (edge.column > 0) {
            PQ.add(
              new SP(
                stacks[edge.row][edge.column - 1],
                Math.max(
                  stacks[edge.row][edge.column - 1] -
                  stacks[edge.row][edge.column],
                  0
                ),
                edge.row,
                edge.column - 1
              )
            );
          }
          if (edge.column < noOfColumns - 1) {
            PQ.add(
              new SP(
                stacks[edge.row][edge.column + 1],
                Math.max(
                  stacks[edge.row][edge.column + 1] -
                  stacks[edge.row][edge.column],
                  0
                ),
                edge.row,
                edge.column + 1
              )
            );
          }
        }
      }

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        out.println(max);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class SP implements Comparable<SP> {

  int vertex;
  int shortestPath;
  int row;
  int column;

  SP(int vertex, int shortestPath, int row, int column) {
    this.vertex = vertex;
    this.shortestPath = shortestPath;
    this.row = row;
    this.column = column;
  }

  @Override
  public int compareTo(SP o) {
    return this.shortestPath - o.shortestPath;
  }
}
