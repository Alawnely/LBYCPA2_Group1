package lbycpa2.module7.othersorting;

public class GnomeSort {
    public static void gnomeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int i = 0;

        // Repeat till index reaches the end of array
        while (i < arr.length) {
            // If you are at the start of the array then go to the right element
            if (i == 0) {
                i++;
            }

            // If the current array element is larger or equal to the previous array element then go one step right
            if (arr[i] >= arr[i - 1]) {
                i++;
            }
            // Else swap these two elements and go one step backwards
            else {
                int temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
                i--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        gnomeSort(arr);

        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
