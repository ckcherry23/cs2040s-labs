// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.*;

public class KattissQuest {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        int noOfCommands = Integer.parseInt(in.readLine());
        TreeMap<Integer, PriorityQueue<Integer>> quests = new TreeMap<Integer, PriorityQueue<Integer>>();

        for (int i = 0; i < noOfCommands; i++) {
          String[] input = in.readLine().split(" ");

          if (input[0].equals("add")) {
            int energy = Integer.parseInt(input[1]);
            int gold = Integer.parseInt(input[2]);
            if (!quests.containsKey(energy)) {
              quests.put(energy, new PriorityQueue<>(1, (a, b) -> b - a));
            }
            quests.get(energy).offer(gold);
          } else {
              int availableEnergy = Integer.parseInt(input[1]);
              Integer largest = quests.floorKey(availableEnergy);
              long totalGold = 0;

              while (availableEnergy > 0 && largest != null) {
                  totalGold += quests.get(largest).poll();
                  if (quests.get(largest).isEmpty()) {
                      quests.remove(largest);
                  }
                  availableEnergy -= largest;
                  largest = quests.floorKey(availableEnergy);
              }

              out.println(totalGold);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
