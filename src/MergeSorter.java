

import java.util.Arrays;


/**
 * Sort using recursive merge sort.
 * 
 * @author Samuel A. Rebelsky
 * @author Your Name Here.
 */
public class MergeSorter
    extends
      SorterBridge
{
  /**
   * Sort vals using iterative merge sort. See the Sorter interface for
   * additional details.
   */
  @Override
  public int[] sort(int[] vals)
  {
    // Base case: Singleton arrays need not be sorted.
    if (vals.length <= 1)
      {
        return vals.clone();
      } // if length <= 1
    else
      {
        int mid = vals.length / 2;
        int[] left = sort(Arrays.copyOfRange(vals, 0, mid));
        int[] right = sort(Arrays.copyOfRange(vals, mid, vals.length));
        return Utils.merge(left, right);
      } // recursive case: More than one element
  } // sort(int[], Comparator)
  public static void main(String[] args)
  {
      int[] arr = {4,3,2,1};
      MergeSorter m = new MergeSorter();
      System.out.println(Arrays.toString(m.sort(arr)));
  }
} // MergeSorter
