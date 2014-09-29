package edu.grinnell.vht.hw4;

import java.util.Arrays;
import java.util.Random;

/**
 * A variety of utilities for working with sorting algorithms. Most are designed
 * to help with testing or experiments.
 * 
 * @author Samuel A. Rebelsky 
 * Edited by: Hannah, Ty, and V
 */
class Utils {
    // +---------------+---------------------------------------------------
    // | Static Fields |
    // +---------------+

    /**
     * A random number generator for use in permutations and such.
     */
    static Random generator = new Random();

    // +----------------+--------------------------------------------------
    // | Static Methods |
    // +----------------+

    /**
     * Merge the values in arrays a1 and a2 into a new array.
     * 
     * @return merged, an array
     * 
     * @pre sorted(a1, order)
     * @pre sorted(a2, order)
     * @post sorted(merged, order).
     * @post merged is a permutation of the concatenation of a1 and a2.
     */
    public static int[] merge(int[] a1, int[] a2) {
	return merge(a1, 0, a1.length, a2, 0, a2.length);
    } // merge(Comparator , int[], int[])

    /**
     * Merge the values in subarrays of a1 and a2 into a new array. The subarray
     * of a1 takes on indices lb1 (inclusive) to ub1 (exclusive). The subarray
     * of a2 takes on indices lb2 (inclusive) to ub2 (exclusive).
     * 
     * @return merged, an array
     * 
     * @pre 0 <= lb1 <= ub1 <= a1.length.
     * @pre 0 <= lb2 <= ub2 <= a2.length.
     * @pre sorted(a1, order, lb1, ub1).
     * @pre sorted(a2, order, lb2, ub2).
     * @post sorted(merged, order).
     * @post merged is a permutation of the concatenation of the given subarrays
     *       of a1 and a2.
     * 
     *       Loop invariant:
     * 
     *       Let current be the index in the result array; j -- in the a1; k --
     *       in the a2;
     * 
     *       For all j, k and current, if j is in bounds (lb1 <= j < ub1) and
     *       a1[j] <= a2[k], or if j is out of bounds (k > ub2), then
     *       result[current] = a1[j].
     * 
     *       For all j, k and current, if k is in bounds (lb2 <= k < ub2) and
     *       a1[j] >= a2[k], or if k is out of bounds (j > ub1), then
     *       result[current] = a2[k].
     * 
     *       For all 0 <= current < (ub1-lb1) + (ub2-lb2) -1, sorted(result,
     *       order, 0, current)
     */
    // @SuppressWarnings({"unchecked"})
    public static int[] merge(int[] a1, int lb1, int ub1, int[] a2, int lb2,
	    int ub2) {
	// Create the new array for the merged values.
	int[] result = (int[]) new int[(ub1 - lb1) + (ub2 - lb2)];
	int j = lb1;
	int k = lb2;
	int current = 0;
	while (current < (ub1 - lb1) + (ub2 - lb2)) {
	    if (k >= ub2 || (j < ub1 && (a1[j] < a2[k]))) {
		result[current] = a1[j++];
		current++;
	    } else {
		result[current] = a2[k++];
		current++;
	    }
	}// while
	return result;
    } // merge(Comparator , int[], int, int, int[], int, int)

    /**
     * "Randomly" permute an array in place.
     */
    public static int[] permute(int[] values) {
	for (int i = 0; i < values.length; i++) {
	    swap(values, i, generator.nextInt(values.length));
	} // for
	return values;
    } // permute(T)

    /**
     * Generate a "random" sorted array of ascending integers of size n.
     */
    public static int[] randomAscendingInts(int n) {
	if (n == 0) {
	    return new int[0];
	}
	int[] values = new int[n];
	// Start with a positive number so that radix sort works
	values[0] = generator.nextInt(10);
	// Add remaining values. We use a random increment between
	// 0 and 3 so that there are some duplicates and some gaps.
	for (int i = 1; i < n; i++) {
	    values[i] = values[i - 1] + generator.nextInt(4);
	} // for
	return values;
    } // randomSortedInts
    
    /**
     * Generate a "random" sorted array of descending integers of size n.
     */
    public static int[] randomDescendingInts(int n) {
	if (n == 0) {
	    return new int[0];
	}
	int[] values = new int[n];
	// Start with a positive number so that radix sort works
	values[0] = Integer.MAX_VALUE - generator.nextInt(100000);
	// Add remaining values. We use a random increment between
	// 0 and 3 so that there are some duplicates and some gaps.
	for (int i = 1; i < n; i++) {
	    values[i] = values[i - 1] - generator.nextInt(4);
	} // for
	return values;
    } // randomSortedInts

    /**
     * Determine if elements l..(u-1) of an array are in sorted order.
     * 
     * @param values
     *            , the array.
     * @param order
     *            , the comparator that determines the ordering.
     * @param l
     *            , an integer
     * @param u
     *            , an integer
     * @return true if the subarray is ordered, false otherwise
     * @pre order can be applied to any two values in the array.
     * @pre 0 <= l <= values.length
     * @pre 0 <= u <= values.length
     */
    public static boolean sorted(int[] values, int l, int u) {
	for (int i = u - 1; i > l; i--) {
	    if (values[i - 1] >= values[i])
		return false;
	} // for
	  // At this point, we've checked every pair. It must be sorted
	return true;
    } // sorted

    /**
     * Determine if an array is sorted.
     * 
     * @param values
     *            , the array.
     * @param order
     *            , the comparator that determines the ordering.
     * @return true if the subarray is ordered, false otherwise
     * @pre order can be applied to any two values in the array.
     */
    public static boolean sorted(int[] values) {
	return sorted(values, 0, values.length);
    } // sorted( int[], Comparator )

    /**
     * Swap two elements in an array.
     * 
     * @param values
     *            , the array
     * @param i
     *            , one of the indices
     * @param j
     *            , another index
     * @pre 0 <= i,j < values.length
     * @pre a = values[i]
     * @pre b = values[j]
     * @post values[i] = b
     * @post values[j] = a
     */
    public static void swap(int[] values, int i, int j) {
	int tmp = values[i];
	values[i] = values[j];
	values[j] = tmp;
    } // swap( int[], int, int)

    public static void main(String[] args) {

	int[] ints1 = { new Integer(1), new Integer(3) };
	int[] ints2 = { new Integer(2), new Integer(4) };
	int[] result = (int[]) Utils.merge(ints1, ints2);
	System.out.println(Arrays.toString(result));
    }// main
} // class Utils
