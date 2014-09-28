

import static org.junit.Assert.*;

import java.util.Arrays;


/**
 * A variety of utilities to assist us in testing sorting routines.

 * @author Samuel A. Rebelsky
 */
public class TestUtils
{

  // +---------+---------------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Check whether sorting was successful
   */
  public static  void checkResults(int[] sorted, int[] values, int[] resorted)
  {
    // System.out.println(Arrays.toString(values));
    if (!Arrays.equals(sorted, resorted))
      {
        fail("Sorting failed\n" + "Original: " + Arrays.toString(values) + "\n"
             + "Resorted: " + Arrays.toString(resorted) + "\n" + "Sorted:   "
             + Arrays.toString(sorted));
      } // if the resorted does not match the expected
  } // checkResults

  /**
   * Test one permutation of sorted.
   */
  public static  void testOnePermutation(Sorter sorter,
                                             int[] sorted)
  {
    int[] values = sorted.clone();
    Utils.permute(values);
    int[] resorted = sorter.sort(values);
    checkResults(sorted, values, resorted);
  } // testOnePermutation

  /**
   * Build all permutations of sorted and see if they sort properly.
   */
  public static  void testAllPermutations(Sorter sorter,
                                              int[] sorted)
  {
    testAllPermutationsKernel(sorter, sorted, sorted.clone(),
                              sorted.length);
  } // testAllPermutations(Sorter, Comparator, int[])

  /**
   * For each permutation of the first n elements of values, test if the
   * sorted version of values is equal to sorted.
   */
  public static  void
    testAllPermutationsKernel(Sorter sorter,
                              int[] sorted, int[] values, int n)
  {
    // Base case: We're out of elements
    if (n <= 0)
      {
        int[] resorted = sorter.sort(values);
        checkResults(sorted, values, resorted);
      }
    else
      {
        for (int i = 0; i < n; i++)
          {
            Utils.swap(values, i, n - 1);
            testAllPermutationsKernel(sorter, sorted, values, n - 1);
            Utils.swap(values, i, n - 1);
          } // for
      } // recursive case, n > 0
  } // testAllPermutations

  // +---------------+---------------------------------------------------
  // | Generic Tests |
  // +---------------+

  /**
   * Test all permutations of a simple array of integers.
   */
  public static void test1(Sorter sorter)
  {
    testAllPermutations(sorter, 
                        new int[] { 0, 1, 1, 2, 4, 7, 9, 11, 13, 13 });
  } // test1

  /**
   * Test a bunch of random permutations.
   */
  public static  void test2(Sorter sorter)
  {
    for (int i = 1; i < 20; i++)
      {
        testOnePermutation(sorter, 
                           Utils.randomSortedInts(i * 20));
      }
  } // test2(Sorter<Integer>)

} // class TestUtils
