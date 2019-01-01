package rsn170330.sp03;

/**
 * CS 5V81.001: Implementation of DSA - Short Project 3
 * Bounded-size Binary Heap implementation using Priority Queue
 * @author Bharath Rudra - bxr180008
 * @author Rahul Nalawade - rsn170330
 */

import java.util.Comparator;
import java.util.Scanner;

// Bounded-size Binary Heap implementation using Priority Queue
public class BinaryHeap<T extends Comparable<? super T>> {
	T[] pq; // priority queue/ pq of generic type T
	int maxSize; // CAPACITY of pq
	int size; // current size of pq
	T temp;

	Comparator<T> comp;
	private static Scanner in;
	
	// The main constructor
	public BinaryHeap(T[] q) {
		// Use a lambda expression to create comparator from compareTo
		this(q, (T a, T b) -> a.compareTo(b));
		size = 0; // empty heap
	}

	// Constructor for building an empty priority queue with custom comparator
	public BinaryHeap(T[] q, Comparator<T> c) {
		pq = q;
		comp = c;
	}

	/**
	 * Build a priority queue with a given array q, using q[0..n-1]. It is not
	 * necessary that n == q.length. Extra space available can be used to add new
	 * elements. Implement this if solving optional problem. To be called from heap
	 * sort.
	 * 
	 */
	private BinaryHeap(T[] q, Comparator<T> c, int n) {
		pq = q;
		comp = c;
	}
	
	/**
	 * Add number to the priority queue, throw exception if pq is full
	 * @param x the element to be added
	 * @throws Exception when pq is full
	 */
	public void add(T x) throws Exception { 
		
		if (size == pq.length) {
			throw new Exception("Queue is full");
		} else {
			pq[size] = x; // Adding to the leaf
			percolateUp(size); // Moving to appropriate place
			size++;
			System.out.println("element added");
		}
	}
	
	/**
	 * Offer(add) a number x, return false if pq is full
	 * @param x the number to be offered
	 * @return isOfferef? true when added, else false
	 */
	public boolean offer(T x) { 
		if (size == pq.length) {
			return false;
		} else {
			pq[size] = x; // Adding to the leaf
			percolateUp(size); // Moving to the appropriate place
			size++;
			return true;
		}
	}
	
	/**
	 * Removes an element from the pq, throw exception if pq is empty
	 * @return the element that was removed
	 * @throws Exception when pq is empty
	 */
	public T remove() throws Exception { 
		if (size == 0) {
			throw new Exception("Queue is empty");
		} else {
			T temp = pq[0]; // The first element which is to be removed
			pq[0] = pq[size - 1];
			size--;
			percolateDown(0); // Moving newly added element to appropriate place
			System.out.println("Deleted element is: ");
			return temp;
		}
	}
	
	/**
	 * Poll(removes) the element, return null if pq is empty
	 * @return true when poll is successful, false otherwise
	 */
	public T poll() { 
		if (size == 0) {
			return null;
		} else {
			temp = pq[0]; // The first element which is to be removed
			pq[0] = pq[size - 1];
			size--;
			percolateDown(0); // Moving newly added element to appropriate place
			return temp;
		}
	}
	
	/**
	 * See the first element (head of the queue), return null if pq is empty
	 * @return the first element
	 */
	public T peek() { 
		if (size == 0) {
			return null;
		} else {
			return pq[0];
		}
	}

	/**
	 * move the index up in the tree
	 * @param i the index to be moved
	 */
	void percolateUp(int i) {
		T x = pq[i];
		//pq[i] may violate heap order with parent
		while (i > 0 && ((pq[parent(i)].compareTo(x)) == 1)) {
			pq[i] = pq[parent(i)];
			i = parent(i);
		}		
		pq[i] = x;
	}

	/**
	 * move the index down the tree
	 * @param i the index to be moved
	 */
	void percolateDown(int i) {
		T x = pq[i];
		int c = (2 * i) + 1;
		// pq[i] may violate heap order with children
		while (c <= size - 1) {
			if (c < size - 1 && pq[c].compareTo(pq[c + 1]) == 1)
				c = c + 1;
			if (x.compareTo(pq[c]) <= 0)
				break;
			pq[i] = pq[c];
			i = c;
			c = 2 * i + 1;

		}
		pq[i] = x;
	}

	// Assign x to pq[i]. Indexed heap will override this method
	void move(int i, T x) {
		pq[i] = x;
	}
	
	// returning parent of index i
	int parent(int i) {
		return (i - 1) / 2;
	}
	
	// returning left child of index i
	int leftChild(int i) {
		return 2 * i + 1;
	}
	
	/** Create a heap. Precondition: none. */
	void buildHeap() {
		for (int i = parent(size - 1); i >= 0; i--) {
			percolateDown(i);
		}
	}

	public static void main(String[] args) throws Exception {
		in = new Scanner(System.in);
		Integer[] q = new Integer[5];
		int x;
		BinaryHeap<Integer> b = new BinaryHeap<>(q);

		System.out.println("Enter choices 1.add() 2.offer() 3.remove() 4.poll() 5.peek()");
		while (in.hasNext()) {
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				System.out.println("enter an element to add");
				x = in.nextInt();
				b.add(x);
				break;

			case 2:
				System.out.println("Enter an element to add");
				x = in.nextInt();
				System.out.println(b.offer(x));
				break;

			case 3:
				System.out.println(b.remove());
				break;

			case 4:
				System.out.println(b.poll());
				break;

			case 5:
				System.out.println(b.peek());
				break;

			}
		}
	}
}
