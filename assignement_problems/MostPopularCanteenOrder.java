
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MostPopularCanteenOrder {

    static String[] mostPopular(String[] orders) {
        HashMap<String, Integer> frequency = new HashMap<>();

        for (String item : orders) {
            frequency.put(
                item, frequency.getOrDefault(item, 0) + 1
            );
        }

        String bestItem = orders[0];
        int bestCount = frequency.get(bestItem);

        for (String item : orders) {
            int count = frequency.get(item);

            if (count > bestCount) {
                bestItem = item;
                bestCount = count;
            }
        }

        return new String[]{
            bestItem, String.valueOf(bestCount)
        };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();

        String[] orders = new String[n];

        System.out.println("Enter item names:");
        for (int i = 0; i < n; i++) {
            orders[i] = sc.next().toLowerCase();
        }

        String[] result = mostPopular(orders);

        System.out.println("(" + result[0] + ", "
                + result[1] + ")");

        sc.close();
    }
}