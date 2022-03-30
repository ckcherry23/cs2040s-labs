// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.*;

public class Conformity {

  public static void main(String[] args) {
    try {
      int largest = 0, count = 0;

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      int noOfStudents = Integer.parseInt(in.readLine());

      HashMap<HashSet<Integer>, Integer> studentCourses = new HashMap<HashSet<Integer>, Integer>(
        noOfStudents
      );

      for (int i = 0; i < noOfStudents; i++) {
        String[] input = in.readLine().split(" ");
        HashSet<Integer> courses = new HashSet<Integer>(input.length);
        for (int j = 0; j < input.length; j++) {
          courses.add(Integer.parseInt(input[j]));
        }
        studentCourses.compute(courses, (k, v) -> (v == null) ? 1 : v + 1);
      }

      for (Map.Entry<HashSet<Integer>, Integer> coursesEntry : studentCourses.entrySet()) {
        if (coursesEntry.getValue() > largest) {
          largest = coursesEntry.getValue();
        }
      }

      for (Map.Entry<HashSet<Integer>, Integer> coursesEntry : studentCourses.entrySet()) {
        if (coursesEntry.getValue() == largest) {
          count++;
        }
      }

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        out.println(largest * count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
