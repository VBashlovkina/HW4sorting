import java.util.Arrays;

public class RadixSorter {

    /**
     * Get the max number of digits used in the ints from given array
     * 
     * @param input
     * @return the number of digits of the maximum value in input
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
/**
 * sorts the input so that for elements i, j, if index[i] < index[j], i < j 
 * @param input
 * @return input with elements re-arranged as specified
 */
    public static int[] radixSort(int[] input) {
	int k;
	int len = input.length;
	int maxDigit = maxDigits(input);
	//there are 10 choices for the current digit
	//no choice can have more numbers than the input length
	int[][] table = new int[10][len];
	int[] indices = new int[10];
	
	for (int digitPlace = 0; digitPlace <= maxDigit; digitPlace++) {
	    Arrays.fill(indices, 0);
	    
	    for (int j = 0; j < len; j++) {
		// Get the digit at current digit place
		int dig = digitAt(input[j], digitPlace);
		
		// Put the number in the appropriate digit column
		table[dig]
			[indices[dig]] = 
			input[j];
		
		// move the index for that digit forward
		indices[dig]++;
	    } // for each input number
	    
	    
	    //flatten the output into one long array
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
