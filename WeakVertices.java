// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;

public class WeakVertices {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        while (true) {
          int noOfVertices = Integer.parseInt(in.readLine());
          if (noOfVertices != -1) {
            int adjMatrix[][] = new int[noOfVertices][noOfVertices];
            int strongVertices[] = new int[noOfVertices];

            for (int i = 0; i < noOfVertices; i++) {
              String input[] = in.readLine().split(" ");
              for (int j = 0; j < noOfVertices; j++) {
                adjMatrix[i][j] = Integer.parseInt(input[j]);
              }
            }

            for (int i = 0; i < noOfVertices; i++) {
              for (int j = 0; j < noOfVertices; j++) {
                for (int k = 0; k < noOfVertices; k++) {
                  if (
                    adjMatrix[i][j] == 1 &&
                    adjMatrix[j][k] == 1 &&
                    adjMatrix[k][i] == 1
                  ) {
                    strongVertices[i] = 1;
                    strongVertices[j] = 1;
                    strongVertices[k] = 1;
                  }
                }
              }
            }
            for (int i = 0; i < noOfVertices; i++) {
              if (strongVertices[i] == 0) {
                out.printf("%d ", i);
              }
            }
            out.printf("\n");
          } else {
            break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
