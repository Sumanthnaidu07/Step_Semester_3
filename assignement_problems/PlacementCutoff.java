
public class PlacementCutoff {

    static int kthHighest(int[] marks, int k) {
        int target = marks.length - k;

        int left = 0;
        int right = marks.length - 1;

        while (left <= right) {
            int pivotIndex = partition(marks, left, right);

            if (pivotIndex == target) {
                return marks[pivotIndex];
            } 
            else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } 
            else {
                right = pivotIndex - 1;
            }
        }

        return -1;
    }

    static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int[] marks = {72, 95, 60, 88, 79};

        int k = 2;

        System.out.println("K-th Highest Mark: " +
                kthHighest(marks, k));
    }
}
