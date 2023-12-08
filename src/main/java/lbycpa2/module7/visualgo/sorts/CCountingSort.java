/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lbycpa2.module7.visualgo.sorts;

import lbycpa2.module7.visualgo.util.CompareValue;
import lbycpa2.module7.visualgo.util.IComparable;

/**
 * Implementation of the counting sort algorithm.
 *
 */
public final class CCountingSort extends AbstractSort {

    public static final CCountingSort SINGLETON = new CCountingSort();

    /** Implementation of the selection sort algorithm */
    private CCountingSort() { }

    /**
     * Starts of the counting sort algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex the lowest index position in the array
     * @param highIndex the highest index position in the array
     */
    @Override
    public void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        // Find the maximum value in the array to determine the range of values
        CompareValue max = (CompareValue) numbers[0];
        for (IComparable num : numbers) {
            if (num.compare(max) == IComparable.GREATER) {
                max = (CompareValue) num;
            }
            count();
        }

        // Create a counting array to store the count of each element
        int[] countArray = new int[max.getValue() + 1];

        // Count the occurrences of each element
        for (IComparable num : numbers) {
            num.compare(num);
            countArray[((CompareValue) num).getValue()]++;
            count();
        }

        // Calculate the cumulative sum
        for (int i = 1; i <= max.getValue(); i++) {
            countArray[i] += countArray[i - 1];
        }

        int[] arrCopy = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arrCopy[i] = ((CompareValue) numbers[i]).getValue();
        }

        // Reconstruct the sorted array
        for (int i = numbers.length - 1; i >= 0; i--) {
            CompareValue num = (CompareValue) numbers[countArray[arrCopy[i]] - 1];
            num.compare(num);
            num.setValue(arrCopy[i]);
            countArray[arrCopy[i]]--;
        }
    }
}
