package edu.grinnell.vht.hw4;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * A variety of utilities to support experiments with sorting algorithms.
 * 
 * @author Samuel A. Rebelsky
 * Edited by: Hannah, Ty, and V
 */
public class ExptUtils {
    // +-----------+-------------------------------------------------------
    // | Utilities |
    // +-----------+

    /**
     * Check the result of sorting. Given an array, a known sorted version of
     * the array, and a candidate sorted version of the array, determines if the
     * candidate is correct. Also prints other useful log info.
     */
    public static void checkSorting(PrintWriter pen, int[] values,
	    int[] sorted, int[] candidate) {
	// Print a quick prefix so that we can see whether or not the
	// sort worked.
	if (Arrays.equals(sorted, candidate)) {
	    pen.print("OK:  ");
	} else {
	    pen.print("BAD: ");
	} // if the sorted array does not equal the original array.

	// Print the transformation for folks who like to look.
	pen.println("sort(" + Arrays.toString(values) + ") => ");
	pen.println("          " + Arrays.toString(candidate));
    } // checkSorting

    // +--------------------+----------------------------------------------
    // | Simple Experiments |
    // +--------------------+

    /**
     * A simple experiment in permutations.
     */
    public static void permutationExperiment(PrintWriter pen, Sorter sorter,
	    int[] sorted) {
	int[] values = sorted.clone();
	Utils.permute(values);
	checkSorting(pen, values, sorted, sorter.sort(values));
    } // permutationExperiment(PrintWriter, Sorter<T>, Comparator<T>

    // +-----------------------+-------------------------------------------
    // | Groups of Experiments |
    // +-----------------------+

    /**
     * Run some experiments using an integer sorter.
     */
    public static void iExperiments(Sorter sorter) {
	PrintWriter pen = new PrintWriter(System.out, true);
	int[] vals1 = new int[] { 1, 2, 2, 2, 4, 5, 7, 7, 11, 13 };

	// A case that's proven problematic. (Why is it problematic? Because
	// people sometimes screw up double values and an element at the end of
	// the array.)
	int[] vals2 = new int[] { 1, 1, 2, 3, 4, 5, 7, 9, 11, 13, 13, 0 };
	checkSorting(pen, vals2, new int[] { 0, 1, 1, 2, 3, 4, 5, 7, 9, 11, 13,
		13 }, sorter.sort(vals2));

	// Five random permutation experiments seems like enough.
	for (int i = 0; i < 5; i++) {
	    permutationExperiment(pen, sorter, vals1);
	} // for

	// A permutation experiment with different sizes
	for (int i = 1; i < 5; i++) {
	    permutationExperiment(pen, sorter,

	    Utils.randomAscendingInts(i * 10));
	} // for
    } // experiments(Sorter<Integer>)

} // class ExptUtils
