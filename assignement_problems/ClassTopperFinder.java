
import java.util.Scanner;

public class ClassTopperFinder {

    static int[] findTopper(int[][] marks) {
        int bestIndex = 0;
        int bestTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;

            for (int j = 0; j < marks[i].length; j++) {
                total += marks[i][j];
            }

            if (total > bestTotal) {
                bestTotal = total;
                bestIndex = i;
            }
        }

        return new int[]{bestIndex, bestTotal};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int m = sc.nextInt();

        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();

        int[][] marks = new int[m][n];

        System.out.println("Enter marks:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                marks[i][j] = sc.nextInt();
            }
        }

        int[] result = findTopper(marks);

        System.out.println("(" + result[0] + ", "
                + result[1] + ")");

        sc.close();
    }
}