import java.util.Arrays;

public class RadixSorter {

    /**
     * Get the max number of digits used in the ints from given array
     * 
     * @param input
     * @return
     */
    private static int maxDigits(int[] input) {
	if (input.length <= 0) {
	    return 0;
	}
	
	int max = input[0];
	for (int i = 1; i < input.length; i++) {
	    if (input[i] > max)
		max = input[i];
	    max = input[i] > max ? input[i] : max;
	}
	
	return new Integer(max).toString().length() - 1;
    }

    public static int digitAt(int num, int digitPlace) {
	return (num / (int) Math.pow(10, digitPlace)) % 10;
	// return (new Integer(num).toString().charAt());
    }

    public static int[] radixSort(int[] input) {
	/*
	 * How do we determine how many digits there are? Maybe we find the max
	 * element and count digits?
	 */
	int k;
	int len = input.length;
	int maxDigit = maxDigits(input);
	int[][] table = new int[10][len];
	int[] indices = new int[10];
	
	for (int digitPlace = 0; digitPlace <= maxDigit; digitPlace++) {
	    Arrays.fill(indices, 0);
	    
	    for (int j = 0; j < len; j++) {
		// Get the digit at current digit place
		int dig = digitAt(input[j], digitPlace);
		
		// Put the digit in the appropriate column
		table[dig]
			[indices[dig]] = 
			input[j];
		
		// move the index for that digit forward
		indices[dig]++;
	    } // for each input number
	    
	    k = 0;
	    
	    for (int i = 0; i < 10; i++) {
		for (int j = 0; j < indices[i]; j++) {
		    input[k++] = table[i][j];
		} // for each number in each digit
	    }// for each digit
	} // for each digit
	return input;
    }

    public static void main(String[] args) {
	//System.out.println("3d digit of 4635 is " + digitAt(2, 2));
	int[] array = {14,2,3,42,586,6,79,82,9,Integer.MAX_VALUE, 0};

	System.out.println(Arrays.toString(array));
	System.out.println(Arrays.toString(radixSort(array)));
    }

}
