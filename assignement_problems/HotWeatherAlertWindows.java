
import java.util.Scanner;

public class HotWeatherAlertWindows {

    static int countAlerts(int[] readings, int k,
                           int threshold) {
        int n = readings.length;
        int sum = 0;
        int alerts = 0;

        // Calculate the first window sum
        for (int i = 0; i < k; i++) {
            sum += readings[i];
        }

        if (sum >= k * threshold) {
            alerts++;
        }

        // Slide the window forward
        for (int i = k; i < n; i++) {
            sum += readings[i];
            sum -= readings[i - k];

            if (sum >= k * threshold) {
                alerts++;
            }
        }

        return alerts;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of readings: ");
        int n = sc.nextInt();

        int[] readings = new int[n];

        System.out.println("Enter temperature readings:");
        for (int i = 0; i < n; i++) {
            readings[i] = sc.nextInt();
        }

        System.out.print("Enter window size k: ");
        int k = sc.nextInt();

        System.out.print("Enter threshold: ");
        int threshold = sc.nextInt();

        System.out.println(
            countAlerts(readings, k, threshold)
        );

        sc.close();
    }
}