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
package lbycpa2.module7.visualgo.util;

import java.time.LocalTime;

/**
 * Performs the counting for the algorithms iterations and provides access to
 * the count.
 * From https://github.com/EricCanull/fxsortinganimation
 */
public final class Logger {

    private static long count;
    public static long startNanoTime, endNanoTime;
    public static LocalTime startTime, endTime;

    /**
     * Resets the iteration count.
     */
    public static void initiateLog() {
        count = 0;
        startNanoTime = System.nanoTime();
        startTime = LocalTime.now();
    }

    public static void terminateLog() {
        endNanoTime = System.nanoTime();
        endTime = LocalTime.now();
    }

    /**
     * Gets the iteration count.
     *
     * @return The value for the iteration count
     */
    public static long getCount() {
        return count;
    }

    /**
     * Increments the count.
     */
    public static void count() {
        count++;
    }
}
