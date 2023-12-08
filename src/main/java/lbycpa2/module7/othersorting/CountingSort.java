package lbycpa2.module7.othersorting;

public class CountingSort {
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // Find the maximum value in the array to determine the range of values
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        // Create a counting array to store the count of each element
        int[] countArray = new int[max + 1];

        // Count the occurrences of each element
        for (int num : arr) {
            countArray[num]++;
        }

        // Calculate the cumulative sum
        for (int i = 1; i <= max; i++) {
            countArray[i] += countArray[i - 1];
        }

        int[] arrCopy = arr.clone();

        // Reconstruct the sorted array
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[countArray[arrCopy[i]] - 1] = arrCopy[i];
            countArray[arrCopy[i]]--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        countingSort(arr);

        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
