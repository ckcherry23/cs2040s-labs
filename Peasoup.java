import java.util.*;

public class Peasoup {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int noOfRestaurants = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < noOfRestaurants; i++) {
      String x = parseRestaurant(sc);
      if (!x.equals("")) {
        System.out.println(x);
        return;
      }
    }

    sc.close();
    System.out.println("Anywhere is fine I guess");
    return;
  }

  static String parseRestaurant(Scanner sc) {
    int noOfFoodItems = sc.nextInt();
    sc.nextLine();
    String restaurantName = sc.nextLine();
    boolean hasPeaSoup = false; 
    boolean hasPancakes = false;

    for (int i = 0; i < noOfFoodItems; i++) {
      String foodItem = sc.nextLine();
      
      if (foodItem.equals("pea soup")) {
        hasPeaSoup = true;
      } else if (foodItem.equals("pancakes")) {
        hasPancakes = true;
      } else {}
    }

    if (hasPeaSoup && hasPancakes) {
      return restaurantName;
    } else {
      return "";
    }
  }
}