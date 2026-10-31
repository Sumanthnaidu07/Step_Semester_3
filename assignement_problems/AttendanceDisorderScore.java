
public class AttendanceDisorderScore {

    static long countInversions(int[] roll) {
        int[] temp = new int[roll.length];

        return mergeSort(roll, temp, 0, roll.length - 1);
    }

    static long mergeSort(int[] arr, int[] temp,
                          int left, int right) {

        long count = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSort(arr, temp, left, mid);
            count += mergeSort(arr, temp, mid + 1, right);

            count += merge(arr, temp, left, mid, right);
        }

        return count;
    }

    static long merge(int[] arr, int[] temp,
                      int left, int mid, int right) {

        int i = left;
        int j = mid + 1;
        int k = left;

        long count = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int x = left; x <= right; x++) {
            arr[x] = temp[x];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] roll = {2, 4, 1, 3, 5};

        long result = countInversions(roll);

        System.out.println("Inversion Count: " + result);
    }
}
