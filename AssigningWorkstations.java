// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AssigningWorkstations {

  static int savedUnlockings = 0;

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      String[] input = in.readLine().split(" ");
      int noOfResearchers = Integer.parseInt(input[0]);
      int timeLimit = Integer.parseInt(input[1]);

      ArrayList<Researcher> researcherList = new ArrayList<Researcher>(
        noOfResearchers
      );

      for (int i = 0; i < noOfResearchers; i++) {
        String[] researchInput = in.readLine().split(" ");
        Researcher researcher = new Researcher(
          Integer.parseInt(researchInput[0]),
          Integer.parseInt(researchInput[1])
        );
        researcherList.add(researcher);
      }

      researcherList.sort(null);

      PriorityQueue<Inactivity> inactivityList = new PriorityQueue<Inactivity>();

      for (Researcher r : researcherList) {
        checkForInactivity(r, inactivityList, timeLimit);
      }
      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        out.println(savedUnlockings);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void checkForInactivity(
    Researcher r,
    PriorityQueue<Inactivity> pq,
    int timeLimit
  ) {
    Inactivity inactivity = pq.peek();
    if (inactivity == null) {
      pq.offer(new Inactivity(r.arrivalTime, r.duration, timeLimit));
    } else if (r.arrivalTime > inactivity.endTime) {
      pq.poll();
      checkForInactivity(r, pq, timeLimit);
    } else if (
      r.arrivalTime >= inactivity.startTime &&
      r.arrivalTime <= inactivity.endTime
    ) {
      savedUnlockings++;
      pq.poll();
      pq.offer(new Inactivity(r.arrivalTime, r.duration, timeLimit));
    } else {
      pq.offer(new Inactivity(r.arrivalTime, r.duration, timeLimit));
    }
  }
}

class Researcher implements Comparable<Researcher> {

  Integer arrivalTime;
  int duration;

  Researcher(int arrivalTime, int duration) {
    this.arrivalTime = arrivalTime;
    this.duration = duration;
  }

  @Override
  public int compareTo(Researcher o) {
    return this.arrivalTime.compareTo(o.arrivalTime);
  }
}

class Inactivity implements Comparable<Inactivity> {

  Integer startTime;
  int endTime;

  Inactivity(int arrivalTime, int duration, int timeLimit) {
    this.startTime = arrivalTime + duration;
    this.endTime = startTime + timeLimit;
  }

  @Override
  public int compareTo(Inactivity o) {
    return this.startTime.compareTo(o.startTime);
  }
}
