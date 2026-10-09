
import java.util.Scanner;

public class TicketPriceSlotFinder {

    static int findSlot(int[] prices, int newPrice) {
        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid;
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of prices: ");
        int n = sc.nextInt();

        int[] prices = new int[n];

        System.out.println("Enter sorted ticket prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        System.out.print("Enter new ticket price: ");
        int newPrice = sc.nextInt();

        System.out.println(findSlot(prices, newPrice));

        sc.close();
    }
}