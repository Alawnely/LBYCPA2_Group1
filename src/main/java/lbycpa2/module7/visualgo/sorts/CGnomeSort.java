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

import lbycpa2.module7.visualgo.util.IComparable;

/**
 *  Implementation of the insertion sort algorithm.
 *
 * @author Eric Canull
 */
public final class CGnomeSort extends AbstractSort {

    public static final CGnomeSort SINGLETON = new CGnomeSort();

    /** Implementation of the insertion sort algorithm. */
    private CGnomeSort() { }

    /**
     * Starts the gnome sort algorithm
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex  the lowest index position in the array
     * @param highIndex the highest index position in the array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        // Sub-array used to hold the sorted numbers
        IComparable temp;

        int i = 0;

        // Repeat till index reaches the end of array
        while (i < numbers.length) {
            // If you are at the start of the array then go to the right element
            if (i == 0) {
                i++;
                count();
            }

            // If the current array element is larger or equal to the previous array element then go one step right
            if (numbers[i].compare(numbers[i - 1]) != IComparable.LESS) {
                i++;
                count();
            }
            // Else swap these two elements and go one step backwards
            else {
                temp = numbers[i];
                numbers[i] = numbers[i - 1];
                numbers[i - 1] = temp;
                i--;
                count();
            }
        }
    }
}
