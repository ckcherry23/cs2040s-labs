// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JoinStrings {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      int stringCount = Integer.parseInt(in.readLine());
      int firstString = 0, secondString;
      HashMap<Integer, StringList> strings = new HashMap<Integer, StringList>(
        stringCount
      );
      for (int i = 0; i < stringCount; i++) {
        strings.put(i, new StringList(in.readLine()));
      }

      for (int i = 0; i < stringCount - 1; i++) {
        String[] input = in.readLine().split(" ");
        firstString = Integer.parseInt(input[0]) - 1;
        secondString = Integer.parseInt(input[1]) - 1;
        strings.get(firstString).concat(secondString);
      }

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        printString(strings, firstString, out);
        out.println();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void printString(
    HashMap<Integer, StringList> strings,
    int index,
    PrintWriter out
  ) {
    out.print(strings.get(index).toString());
    ArrayList<Integer> lst = strings.get(index).getStringList();
    for (int i : lst) {
      printString(strings, i, out);
    }
  }
}

class StringList {
  private String str;
  private ArrayList<Integer> concatLst;

  StringList(String str) {
    this.str = str;
    this.concatLst = new ArrayList<Integer>();
  }

  void concat(int concatIndex) {
    concatLst.add(concatIndex);
  }

  ArrayList<Integer> getStringList() {
    return concatLst;
  }

  @Override
  public String toString() {
    return str;
  }
}
