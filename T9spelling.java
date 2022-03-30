// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;

public class T9spelling {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      int[] translate = {
        2, // a
        22, // b
        222, // c
        3, // d
        33,
        333,
        4,
        44,
        444,
        5,
        55,
        555,
        6,
        66,
        666,
        7,
        77,
        777,
        7777,
        8,
        88,
        888,
        9,
        99,
        999,
        9999,
      };
      int noOfCases = Integer.parseInt(in.readLine());

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        for (int i = 1; i <= noOfCases; i++) {
          int lastDigit = -1;
          StringBuilder str = new StringBuilder(in.readLine());
          int len = str.length();
          StringBuilder trans = new StringBuilder();
          out.printf("Case #%d: ", i);
          for (int j = 0; j < len; j++) {
            char curr = str.charAt(j);
            if (curr == ' ') {
              if (lastDigit == 0) {
                trans.append(" ");
              }
              lastDigit = 0;
              trans.append("0");
            } else {
              if (lastDigit == translate[curr - 97] % 10) {
                trans.append(" ");
              }
              lastDigit = translate[curr - 97] % 10;
              trans.append(Integer.toString(translate[curr - 97]));
            }
          }

          out.println(trans);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
