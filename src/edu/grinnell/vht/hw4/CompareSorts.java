package edu.grinnell.vht.hw4;

import java.io.PrintWriter;
import java.util.Random;

public class CompareSorts {
    static Random generator = new Random();

    private static void printHeader(PrintWriter pen) {
	pen.println("\t\t\tData Set\t\t\tTimes");
	pen.println("Algorithm\tSize\tAscending Order\tRandom Order\tDescending Order");
    } // printHeader(PrintWriter)

    /**
     * Gets an array of n random ints
     * 
     * @param n
     * @return an array of n random ints
     */
    private static int[] randomInts(int n) {
	int[] result = new int[n];
	for (int i = 0; i < n; i++) {
	    result[i] = generator.nextInt(Integer.MAX_VALUE);
	}
	return result;
    }// randomInts(int)

    // private static void testSorter(Sorter[] sorters, int numPermutations,
    // double[][][] times, int[] arr, int sorterIndex, int nIndex) {
    // long start;
    // long end;
    // start = System.currentTimeMillis();
    // sorters[j].sort(arr);
    // end = System.currentTimeMillis();
    // // average each runtime
    // times[j][i][0] += (end - start) / (numPermutations * 1.0);
    // }
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
	long start, end;
	/* run sorts */
	java.io.File outFile = new java.io.File("results.txt");
	PrintWriter pen = new PrintWriter(outFile);
	Sorter[] sorters = new Sorter[] { new Quicksorter(), // 0
		new RadixSorter(),// 1
		new HeapSorter(),// 2
		new MergeSorter(), // 3
		new InsertionSorter() };// 4
	String[] sorterNames = { "Quicksort", "Radix Sort", "Heap Sort",
		"Merge Sort", "Insertion Sort" };
	long currentMax = 0;

	printHeader(pen);

	for (int i = 0; currentMax < 10000; i++) {
	    int n = (int) (10000 * Math.pow(2, i));
	    for (int j = 0; j < sorters.length; j++) {

		pen.print(sorterNames[j] + "\t" + n + "\t");

		int[][] testArrays = new int[][] {
			Utils.randomAscendingInts(n), randomInts(n),
			Utils.randomDescendingInts(n) };

		for (int m = 0; m < 3; m++) {

		    start = System.currentTimeMillis();

		    sorters[j].sort(testArrays[m]);

		    end = System.currentTimeMillis();

		    pen.print((end - start) + "\t\t");

		    if (end - start > currentMax)
			currentMax = end - start;
		}// for ascending, random, and descending

		pen.println();
	    }// for each sorter

	    pen.println();
	}// for each value of n
	pen.close();
    }// main
}// class CompareSorts
