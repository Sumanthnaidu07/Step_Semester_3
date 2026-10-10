
import java.util.*;

class ClassMarksGrid {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            int bracket = line.indexOf('[');
            if (bracket == -1) continue;

            String name = line.substring(0, bracket).trim();
            String values = line.substring(bracket + 1)
                                 .replace("]", "")
                                 .trim();

            String[] parts = values.split("\\s*,\\s*");

            if (parts.length != 3) continue;

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(parts[i]);
            }

            names.add(name);
            marksList.add(marks);
        }

        int n = names.size();

        if (n == 0) {
            sc.close();
            return;
        }

        int[][] grid = new int[n][3];
        int[] totals = new int[n];
        int[] subjectTotals = new int[3];

        int topperIndex = 0;

        for (int i = 0; i < n; i++) {
            grid[i] = marksList.get(i);

            for (int j = 0; j < 3; j++) {
                totals[i] += grid[i][j];
                subjectTotals[j] += grid[i][j];
            }

            if (totals[i] > totals[topperIndex]) {
                topperIndex = i;
            }
        }

        System.out.print("Totals ");

        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(", ");

            System.out.print(names.get(i) + " " + totals[i]);
        }

        System.out.println();

        System.out.printf(
            "averages %.2f, %.2f, %.2f%n",
            subjectTotals[0] / (double) n,
            subjectTotals[1] / (double) n,
            subjectTotals[2] / (double) n
        );

        System.out.println(
            "topper " + names.get(topperIndex) +
            " (" + totals[topperIndex] + ")"
        );

        sc.close();
    }
}
