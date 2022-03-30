// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;

public class LostMap {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      int noOfVillages = Integer.parseInt(in.readLine());

      int villageDistance[] = new int[noOfVillages];
      int closestVillage[] = new int[noOfVillages];

      int table[][] = new int[noOfVillages][noOfVillages];
      int MST[][] = new int[noOfVillages][2];
      boolean isConnected[] = new boolean[noOfVillages];

      for (int i = 0; i < noOfVillages; i++) {
        villageDistance[i] = Integer.MAX_VALUE;
        closestVillage[i] = i;
      }

      villageDistance[0] = 0;

      for (int i = 0; i < noOfVillages; i++) {
        String input[] = in.readLine().split(" ");
        for (int j = 0; j < noOfVillages; j++) {
          table[i][j] = Integer.parseInt(input[j]);
        }
      }

      for (int count = 0; count < noOfVillages; count++) {
        int min = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < noOfVillages; i++) {
          if (villageDistance[i] < min) {
            min = villageDistance[i];
            vertex = i;
          }
        }

        MST[count][0] = vertex;
        MST[count][1] = closestVillage[vertex];
        isConnected[vertex] = true;
        isConnected[closestVillage[vertex]] = true;

        villageDistance[vertex] = Integer.MAX_VALUE;

        for (int i = 0; i < noOfVillages; i++) {
          if (!isConnected[i] && villageDistance[i] > table[vertex][i]) {
            villageDistance[i] = table[vertex][i];
            closestVillage[i] = vertex;
          }
        }
      }

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        for (int i = 1; i < noOfVillages; i++) {
          out.printf("%d %d\n", MST[i][0] + 1, MST[i][1] + 1);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
