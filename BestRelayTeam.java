// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;

public class BestRelayTeam {

  public static void main(String args[]) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      int noOfRunners = Integer.parseInt(in.readLine());

      Runner[] firstLeg = new Runner[noOfRunners];
      Runner[] otherLeg = new Runner[noOfRunners];

      double totalTime = 0;
      Runner[] bestRelayTeam = new Runner[4];

      for (int i = 0; i < noOfRunners; i++) {
        String[] line = in.readLine().split(" ");
        Runner runner = new Runner(
          line[0],
          Double.parseDouble(line[1]),
          Double.parseDouble(line[2])
        );
        InsertRunner(runner, firstLeg, otherLeg, i);
      }

      Runner bestFirstLeg = firstLeg[0];
      double shortestTime = 80;

      for (int i = 0; i < noOfRunners; i++) {
        int runnersChosen = 1;
        double currentTime = firstLeg[i].getFirstLegTime();

        for (int j = 0; runnersChosen < 4; j++) {
          if (!otherLeg[j].getName().equals(firstLeg[i].getName())) {
            currentTime += otherLeg[j].getOtherLegTime();
            runnersChosen++;
          }
        }

        if (currentTime < shortestTime) {
          shortestTime = currentTime;
          bestFirstLeg = firstLeg[i];
        }
      }

      totalTime = shortestTime;
      bestRelayTeam[0] = bestFirstLeg;
      int otherLegs = 0;
      for (int i = 1; i < 4; otherLegs++) {
        if (!otherLeg[otherLegs].getName().equals(bestFirstLeg.getName())) {
          bestRelayTeam[i++] = otherLeg[otherLegs];
        }
      }

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        out.println(totalTime);
        
        for (int i = 0; i < 4; i++) {
          out.println(bestRelayTeam[i].getName());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void InsertRunner(
    Runner runner,
    Runner[] firstLeg,
    Runner[] otherLeg,
    int index
  ) {
    int i;

    // firstLeg
    for (
      i = index - 1;
      i >= 0 && firstLeg[i].getFirstLegTime() > runner.getFirstLegTime();
      i--
    ) {
      firstLeg[i + 1] = firstLeg[i];
    }

    firstLeg[i + 1] = runner;

    // otherLeg
    for (
      i = index - 1;
      i >= 0 && otherLeg[i].getOtherLegTime() > runner.getOtherLegTime();
      i--
    ) {
      otherLeg[i + 1] = otherLeg[i];
    }

    otherLeg[i + 1] = runner;
  }
}

class Runner {

  private String name;
  private double firstLegTime;
  private double otherLegTime;

  Runner(String name, double firstLegTime, double otherLegTime) {
    this.name = name;
    this.firstLegTime = firstLegTime;
    this.otherLegTime = otherLegTime;
  }

  String getName() {
    return name;
  }

  double getFirstLegTime() {
    return firstLegTime;
  }

  double getOtherLegTime() {
    return otherLegTime;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
