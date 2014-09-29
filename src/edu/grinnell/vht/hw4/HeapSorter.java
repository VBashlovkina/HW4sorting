package edu.grinnell.vht.hw4;

/*
 * References:
 * How to use abstract classes:
 * http://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
 * How to use compareTo:
 * http://www.tutorialspoint.com/java/java_string_compareto.htm
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author V and Mira
 */
public class HeapSorter extends SorterBridge {

    int currentSize;
    int heapArray[];

    public HeapSorter(int k) {
	currentSize = 0;
	heapArray = new int[k];
    }

    public HeapSorter() {
    }

    /**
     * gets the parent of the element at index
     * 
     * @param index
     * @return the index of the parent of the element at index
     */
    static private int parent(int index) {
	return (index - 1) / 2;
    }

    /**
     * gets left child of the element at index
     * 
     * @param index
     * @return the index of the left child of the element at index
     */
    static private int left(int index) {
	return 2 * index + 1;
    }

    /**
     * gets the right child of the element at index
     * 
     * @param index
     * @return the index of the right child of the element at index
     */
    static private int right(int index) {
	return 2 * index + 2;
    }

    /**
     * This is for testing: determine whether this has the heap property
     * 
     * @return true or false
     */
    public boolean isHeap() {
	for (int i = 0; i < this.currentSize; i++) {
	    if (this.numChildren(i) > 0) {
		if (this.heapArray[i] > heapArray[left(i)]) {
		    return false;
		}
	    }
	    if (this.numChildren(i) > 1) {
		if (this.heapArray[i] > heapArray[right(i)]) {
		    return false;
		}
	    }
	}
	return true;
    }

    public int top() {
	if (isEmpty())
	    throw new NoSuchElementException();
	else
	    return heapArray[0];
    }

    public Boolean isEmpty() {
	return currentSize == 0;
    }

    // returns true if priority queue is full
    public Boolean isFull() {
	return currentSize == heapArray.length;
    }

    public void insert(int item) {
	this.heapArray[this.currentSize] = item;
	this.swapUp();
	this.currentSize++;
    }

    /**
     * swaps the elements at the indicies i1 & i2
     * 
     * @param i1
     * @param i2
     */
    private void swap(int i1, int i2) {
	int tmp = this.heapArray[i1];
	this.heapArray[i1] = this.heapArray[i2];
	this.heapArray[i2] = tmp;
    }

    /**
     * gets the number of children of the element at index
     * 
     * @param index
     * @return the number of children (0, 1, or 2)
     */
    private int numChildren(int index) {
	int num = 0;
	if (left(index) < this.currentSize) {
	    num++;
	}// if
	if (right(index) < this.currentSize) {
	    num++;
	}// if
	return num;
    }// numChildren

    /**
     * "swaps up" elements at the bottom of the heap to restore the heap
     * property
     */
    private void swapUp() {
	int index = this.currentSize; // index of the element you want to swap
	// up
	int val = this.heapArray[this.currentSize];
	while (index >= 1) {
	    if (val < this.heapArray[parent(index)]) {
		this.swap(index, parent(index));
		index = parent(index);
	    } else {
		break;
	    }// if/else
	}// while
    }// swapUp

    /**
     * "swaps down" elements at the top of the heap to restore the heap property
     */
    private void swapDown() {
	int index = 0;
	int smallerChildIndex = 0;
	while (numChildren(index) != 0) {
	    if (numChildren(index) == 1
		    || this.heapArray[left(index)] < this.heapArray[right(index)]) {// broken
		smallerChildIndex = left(index);
	    }// if
	    else {
		smallerChildIndex = right(index);
	    }

	    if (this.heapArray[smallerChildIndex] < this.heapArray[index]) {
		swap(index, smallerChildIndex);
		index = smallerChildIndex;
	    }// if -left/index
	    else {
		break;
	    }// else
	}// while
    }

    public int remove() {
	int top = this.heapArray[0];
	this.heapArray[0] = this.heapArray[this.currentSize - 1];
	this.heapArray[currentSize - 1] = -1;// indicates empty cell
	this.currentSize--;
	this.swapDown();
	return top;
    }

    public String toString() {
	return Arrays.toString(this.heapArray);
    }

    @Override
    public int[] sort(int[] input) {
	int len = input.length;
	HeapSorter pq = new HeapSorter(len);
	for (int i = 0; i < len; i++) {
	    pq.insert(input[i]);
	}// for inserting everything in a heap
	for (int i = 0; i < len; i++) {
	    input[i] = pq.remove();
	}// for sorting
	return input;
    }

    public static void main(String[] args) {

	// Experiment with the heap
	int[] contents = { 2, 1, 3, 4, 2, 16, 4 };
	HeapSorter pq = new HeapSorter(7);
	int i = 0;
	while (!pq.isFull()) {
	    System.out.println("inserted: " + contents[i]);
	    pq.insert(contents[i++]);
	}
	System.out.println(Arrays.toString(pq.heapArray));
	while (!pq.isEmpty()) {
	    System.out.println("top: " + pq.top());
	    System.out.println("removed: " + pq.remove());
	}
	HeapSorter dummy = new HeapSorter(0);
	System.out.println("before sorting " + Arrays.toString(contents));
	contents = dummy.sort(contents);
	System.out.println("after sorting " + Arrays.toString(contents));

	int[] array = { 14, 2, 3, 42, 586, 6, 79, 82, 9, Integer.MAX_VALUE, 0 };
	System.out.println("before sorting " + Arrays.toString(array));
	array = dummy.sort(array);
	System.out.println("after sorting " + Arrays.toString(array));

    }
}
