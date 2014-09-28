

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortTest
{

  @Test
  public void testOne()
  {
    Sorter qsort = new Quicksorter();
    TestUtils.test1(qsort);
  }//testOne
  
  @Test
  public void testTwo()
  {
    Sorter qsort = new Quicksorter();
    TestUtils.test2(qsort);
  }//testTwo
  
}
