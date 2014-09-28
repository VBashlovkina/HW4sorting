import org.junit.Test;

public class HeapSortTest {

    @Test
    public void testOne() {
	Sorter heap = new HeapSorter(0);
	TestUtils.test1(heap);
    }// testOne

    @Test
    public void testTwo() {
	Sorter heap = new HeapSorter(0);
	TestUtils.test2(heap);
    }// testTwo

}
