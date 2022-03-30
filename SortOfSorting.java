// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortOfSorting {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        int noOfNames = Integer.parseInt(in.readLine());

        while (true) {
          ArrayList<String> names = new ArrayList<String>(noOfNames);

          if (noOfNames == 0) {
            return;
          } else {
            for (int i = 0; i < noOfNames; i++) {
              names.add(in.readLine());
            }
          }

          Collections.sort(names, Comparator.comparing(s -> s.substring(0, 2)));

          for (int i = 0; i < noOfNames; i++) {
            out.println(names.get(i));
          }

          noOfNames = Integer.parseInt(in.readLine());
          if (noOfNames != 0) {
            out.println();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
