package edu.grinnell.vht.hw4;

import java.util.Random;

/**
 * Sort using Quicksort.
 * 
 * @author Samuel A. Rebelsky
 * Edited by: Hannah, Ty, and V 
 */
public class Quicksorter extends SorterBridge {
    /**
     * Sort vals using Quicksort. See the Sorter interface for additional
     * details.
     */
    @Override
    public int[] sorti(int[] vals) {
	qsort(vals, 0, vals.length);
	return vals;
    } // sorti(int[], Comparator)

    /**
     * Sort the elements in positions [lb..ub) using Quicksort.
     */
    public void qsort(int[] vals, int lb, int ub) {
	// One element arrays are sorted.
	if (lb >= ub - 1) {
	    return;
	} else {
	    int mid = partition(vals, lb, ub);
	    qsort(vals, lb, mid);
	    qsort(vals, mid + 1, ub);
	} // More than one element
    } // qsort(int[], Comparator, int, int)

    public int pickPivot(int lb, int ub) {
	Random gen = new Random();
	return lb + gen.nextInt(ub - lb);
    }// pickPivot(Comparator, int, int)

    /**
     * Pick a random pivot and reorganize the elements in positions [lb..ub) of
     * vals such that elements smaller than the pivot appear to the left,
     * elements bigger than the pivot appear to the right of the pivot, and the
     * pivot is in the middle.
     * 
     * @param values
     *            , an array.
     * @param order
     *            , a comparator.
     * @param lb
     *            , an integer.
     * @param ub
     *            , an integer.
     * @return mid, the index of the pivot.
     * 
     * @pre order can be applied to any pair of elements in vals.
     * @pre 0 <= lb < ub < values.length.
     * @post lb <= mid < ub
     * @post values[mid] == pivot, for some value pivot
     * @post For all i, lb <= i < mid, order.compare(values[i],pivot) <= 0 For
     *       all i, mid < i < ub, order.compare(pivot, values[i]) < 0
     * 
     *       Loop invariants: All items below the lower bound are smaller than
     *       the pivot, and the values above the upper bound.
     * 
     */
    int partition(int[] vals, int lb, int ub) {
	// Pick a pivot
	int pivot = pickPivot(lb, ub);
	// Move the pivot to lower bound
	Utils.swap(vals, lb, pivot);
	pivot = lb;
	// Initialize movable indices
	int left = lb + 1;
	int right = ub;
	int pVal = (vals[pivot]);

	while (left < right) {
	    if (vals[left] > pVal) // if vals[lb] > pVal
		Utils.swap(vals, --right, left);
	    else
		left++;
	}// while left < right
	Utils.swap(vals, pivot, --left);
	return left;
    } // partition(int[], Comparator, int, int)
} // Quicksorter
