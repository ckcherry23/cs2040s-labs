// Name: Charisma Kausar
// Student Number: A0226593X

import java.io.*;
import java.util.Arrays;

public class CardTrading {

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      String[] inputValues = in.readLine().split(" ");
      int noOfCards = Integer.parseInt(inputValues[0]);
      int noOfCardTypes = Integer.parseInt(inputValues[1]);
      int noOfCombosRequired = Integer.parseInt(inputValues[2]);

      long totalCost = 0;

      String[] inputDeck = in.readLine().split(" ");
      int[] deck = new int[noOfCards];
      for (int i = 0; i < noOfCards; i++) {
        deck[i] = Integer.parseInt(inputDeck[i]);
      }

      Card[] cards = new Card[noOfCardTypes]; // Hashmap

      for (int i = 1; i <= noOfCardTypes; i++) {
        String[] inputPrices = in.readLine().split(" ");
        cards[i - 1] =
          new Card(
            i,
            Long.parseLong(inputPrices[0]),
            Long.parseLong(inputPrices[1])
          );
      }

      for (int i = 0; i < noOfCards; i++) {
        cards[deck[i] - 1].incrementCount();
      }

      Arrays.sort(cards);

      for (int i = 0; i < noOfCardTypes; i++) {
        if (i < noOfCombosRequired) {
          totalCost -= cards[i].getCostPrice() * (2 - cards[i].getCount());
        } else {
          totalCost += cards[i].getSellingPrice() * cards[i].getCount();
        }
      }

      /* Try-with-resources */
      try (
        PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(System.out))
        )
      ) {
        out.println(totalCost);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class Card implements Comparable<Card> {

  int cardType;
  long costPrice;
  long sellingPrice;
  int count;

  Card(int cardType, long costPrice, long sellingPrice) {
    this.cardType = cardType;
    this.costPrice = costPrice;
    this.sellingPrice = sellingPrice;
    this.count = 0;
  }

  // Getters
  int getCardType() {
    return cardType;
  }

  long getCostPrice() {
    return costPrice;
  }

  long getSellingPrice() {
    return sellingPrice;
  }

  int getCount() {
    return count;
  }

  // Functions
  void incrementCount() {
    count++;
  }

  @Override
  public int compareTo(Card c1) {
    return (int) (
      (
        (this.getSellingPrice() * this.getCount()) +
        (this.getCostPrice() * (2 - this.getCount()))
      ) -
      (
        (c1.getSellingPrice() * c1.getCount()) +
        (c1.getCostPrice() * (2 - c1.getCount()))
      )
    );
  }

  @Override
  public String toString() {
    return Integer.toString(this.cardType);
  }
}
