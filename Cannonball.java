// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;

public class Cannonball {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      String a[] = in.readLine().split(" ");
      Coordinate src = new Coordinate(
        Double.parseDouble(a[0]),
        Double.parseDouble(a[1]),
        false
      );

      String b[] = in.readLine().split(" ");
      Coordinate dest = new Coordinate(
        Double.parseDouble(b[0]),
        Double.parseDouble(b[1]),
        false
      );

      int noOfCannons = Integer.parseInt(in.readLine());

      Coordinate coordinates[] = new Coordinate[noOfCannons + 2];

      coordinates[0] = src;
      coordinates[1] = dest;

      for (int i = 0; i < noOfCannons; i++) {
        String input[] = in.readLine().split(" ");
        Coordinate cannon = new Coordinate(
          Double.parseDouble(input[0]),
          Double.parseDouble(input[1]),
          true
        );
        coordinates[i + 2] = cannon;
      }

      double adjacencyMat[][] = new double[noOfCannons + 2][noOfCannons + 2];

      for (int i = 0; i < noOfCannons + 2; i++) {
        for (int j = 0; j < noOfCannons + 2; j++) {
          if (i == j) {
            adjacencyMat[i][j] = 0;
          } else {
            double distance = coordinates[i].distanceTo(coordinates[j]);
            if (coordinates[i].isCannon) {
                if (distance >= 50) {
                    adjacencyMat[i][j] =
                    Math.min(((distance - 50) / 5) + 2, distance / 5);
                } else {
                    adjacencyMat[i][j] =
                    Math.min(((50 - distance) / 5) + 2, distance / 5);
                }
              
            } else {
              adjacencyMat[i][j] = distance / 5;
            }
          }
        }
      }

      for (int k = 0; k < noOfCannons + 2; k++) {
        for (int i = 0; i < noOfCannons + 2; i++) {
          for (int j = 0; j < noOfCannons + 2; j++) {
            adjacencyMat[i][j] =
              Math.min(
                adjacencyMat[i][j],
                adjacencyMat[i][k] + adjacencyMat[k][j]
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
        out.println(adjacencyMat[0][1]);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class Coordinate {

  double x;
  double y;
  boolean isCannon;

  Coordinate(double x, double y, boolean isCannon) {
    this.x = x;
    this.y = y;
    this.isCannon = isCannon;
  }

  public double distanceTo(Coordinate o) {
    return Math.sqrt(Math.pow(this.x - o.x, 2) + Math.pow(this.y - o.y, 2));
  }
}
