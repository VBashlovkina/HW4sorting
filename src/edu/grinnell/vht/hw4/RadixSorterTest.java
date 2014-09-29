package edu.grinnell.vht.hw4;

import org.junit.Test;

public class RadixSorterTest {

    @Test
    public void testOne() {
	Sorter radix = new RadixSorter();
	TestUtils.test1(radix);
    }// testOne

    @Test
    public void testTwo() {
	Sorter radix = new RadixSorter();
	TestUtils.test2(radix);
    }// testTwo

}
