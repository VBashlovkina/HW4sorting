import java.util.Arrays;
import java.util.Random;
/*
 * Run this! It takes a couple minutes. Note that I'm not
 * using MergeSorter because i think it's broken, i'm not sure.
 */
public class CompareSorts {
    static Random generator = new Random();

    private static int[] randomInts(int n) {
	int[] result = new int[n];
	for (int i = 0; i < n; i++) {
	    result[i] = generator.nextInt(Integer.MAX_VALUE);
	}
	return result;
    }// randomInts

    /**
     * @param args
     */
    public static void main(String[] args) {
	long start;
	long end;
	/* run sorts */
	Sorter[] sorters = new Sorter[] {
		new Quicksorter(), //0
		new RadixSorter(),//1
		new HeapSorter(),//2
		// new MergeSorter(),
		new InsertionSorter() };//3
	int numPermutations = 10; // number of permutations to test
	int[] valsOfn = new int[] { 1, 10, 100, 500, 1000, 2500, 5000, 10000,
		20000, 40000 , 80000, 200000};
	double[][] times = new double[valsOfn.length][sorters.length];
	/*
	 * different lengths of arrays different permutations of each array
	 * (like 5 permutations) average time for different permutations
	 */
	int[] array;
	for (int i = 0; i < valsOfn.length; i++) {
	    array = randomInts(valsOfn[i]);
	    for (int j = 0; j < sorters.length; j++) {
		for (int k = 0; k < numPermutations; k++) {
		    start = System.currentTimeMillis();
		    sorters[j].sort(array);
		    end = System.currentTimeMillis();
		    // average each runtime
		    times[i][j] += (end - start) / (numPermutations * 1.0);
		    Utils.permute(array);
		}// for each sorter
	    }// for each permutation
	}// for each value of n

	/* Each subarray represents the results of four sorters
	 * for given n -- from 1 to 200000. Each number is the
	 * runtime in miliseconds. 
	 */
	System.out.println(Arrays.deepToString(times));
	
	
    }// main

}// class CompareSorts
