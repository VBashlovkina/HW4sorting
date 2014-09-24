

import java.util.Comparator;

/**
 * Sort using heap sort.
 */
public class HeapSorter
{
  // +----------------+--------------------------------------------------
  // | Static Methods |
  // +----------------+

  /**
   * Convert an array to a heap
   * 
   * @param <T>
   * @return
   */
  static <T> Heap heapify(T[] values, Comparator<T> order)
  {
    Heap h = new Heap(values.length, order);
    for (int i = 0; i < values.length; i++)
      {
        h.put(values[i]);
      }// for adding elements to the heap
    return h;
  }// heapify (

  /**
   * Sort an array
   * 
   * Loop invariants: everything strictly after cursor is sorted. 
   * Everything else is unsorted.
   */
  public static <T> void sort(T[] values, Comparator<T> order)
  {
    // Convert values to a heap
    Heap h = heapify(values, order);
    values = (T[]) h.values;
    int len = values.length;

    // Sort

    for (int cursor = len -1; cursor >= 0; cursor--)
      { 
       Heap.swap(values, 0, cursor);
       Heap.swapDown(values, len, 0, order);
      }// for 
    
  } // sort(T[], Comparator<T>)
} // class HeapSorter
