// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.ArrayDeque;

public class Islands {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      String counts[] = in.readLine().split(" ");

      int noOfRows = Integer.parseInt(counts[0]);
      int noOfColumns = Integer.parseInt(counts[1]);

      char world[][] = new char[noOfRows][noOfColumns];
      int visited[][] = new int[noOfRows][noOfColumns];

      ArrayDeque<PossibleLand> Q = new ArrayDeque<PossibleLand>();

      int noOfIslands = 0;

      for (int i = 0; i < noOfRows; i++) {
        String input = in.readLine();
        for (int j = 0; j < noOfColumns; j++) {
          world[i][j] = input.charAt(j);
        }
      }

      for (int i = 0; i < noOfRows; i++) {
        for (int j = 0; j < noOfColumns; j++) {
          if (visited[i][j] == 1) {
            continue;
          } else {
            if (world[i][j] == 'L') {
              Q.offer(new PossibleLand(i, j));
              while (!Q.isEmpty()) {
                PossibleLand land = Q.poll();
                int m = land.row;
                int n = land.column;

                if (visited[m][n] == 1) {
                  continue;
                }

                visited[m][n] = 1;

                if (m - 1 > -1) {
                  if (world[m - 1][n] == 'L' || world[m - 1][n] == 'C') {
                    Q.offer(new PossibleLand(m - 1, n));
                  }
                }
                if (m + 1 < noOfRows) {
                  if (world[m + 1][n] == 'L' || world[m + 1][n] == 'C') {
                    Q.offer(new PossibleLand(m + 1, n));
                  }
                }
                if (n - 1 > -1) {
                  if (world[m][n - 1] == 'L' || world[m][n - 1] == 'C') {
                    Q.offer(new PossibleLand(m, n - 1));
                  }
                }
                if (n + 1 < noOfColumns) {
                  if (world[m][n + 1] == 'L' || world[m][n + 1] == 'C') {
                    Q.offer(new PossibleLand(m, n + 1));
                  }
                }
              }
              noOfIslands++;
            }
          }
        }
      }
      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        out.println(noOfIslands);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class PossibleLand {

  int row;
  int column;

  PossibleLand(int row, int column) {
    this.row = row;
    this.column = column;
  }
}
