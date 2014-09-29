package edu.grinnell.vht.hw4;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A bridge between the two types of array sorters. If you implement one of the
 * two array-based sorting routines, this automatically gives you the other.
 * 
 * Warning! Do not try to use this directly. The two implementations here are
 * mutually recursive.
 * 
 * @author Samuel A. Rebelsky
 * Edited by: Hannah, Ty, and V
 */
public class SorterBridge implements Sorter {

    @Override
    public int[] sorti(int[] vals) {
	// Sort
	int[] sorted = sort(vals);
	// And copy back to the original array
	System.arraycopy(sorted, 0, vals, 0, vals.length);
	// We're done
	return vals;
    } // sorti(int[], Comparator)

    @Override
    public int[] sort(int[] values) {
	return sorti(values.clone());
    } // sort(int[], Comparator)
} // SorterBridge
