import java.lang.reflect.Array;


public class Heap<T> implements PriorityQueue<T> {
	private T[] arr;
	private int size;
	private Class<T> classType;

	
	@SuppressWarnings("unchecked")
	public Heap(Class<T> classType, int capacity) {
		this.arr = (T[])Array.newInstance(classType, capacity + 1);
		this.size = 0;
		this.classType = classType;
	}
	
	@SuppressWarnings("unchecked")
	private void grow() {
		// Create a new array of double cap and copy the contents into it. 
		T[] temp = (T[])Array.newInstance(classType, this.arr.length * 2);
		for ( int i = 1; i < this.arr.length; i++ ) {
			temp[i] = arr[i];
		}
		this.arr = temp;
	}
	
	public int leftChild(int current) {
		return current*2;
	}
	
	public int rightChild(int current) {
		return (current*2) + 1;
	}
	
	public int parentIdx(int current) {
		return current/2;
	}
	
	public boolean hasLeftChild(int current) {
		return leftChild(current) <= size;
	}
	
	public boolean hasRightChild(int current) {
		return rightChild(current) <= size;
	}
	
	public boolean hasParent(int current) {
		return current > 1;
	}
	
	
	public void swap(int i1, int i2) {
		// Exchange the elements 
		T temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	public void enqueue(T item) {
		// Resize if out of room.
		if (size == arr.length - 1) {
			grow();
		}
		// Add to the end of the array and bubbleUp. 
		arr[++size] = item;
		bubbleUp(size);
	}
	
	public T dequeue() {
		// Set the max to the front of the array
		T max = peek();
		if ( size > 1) {
			// Put element at back in front and decrease size.
			arr[1] = arr[size];
			size--;
		}
		// Rearrange the elements
		bubbleDown();
		return max;
	}
	
	@SuppressWarnings("unchecked")
	public void bubbleUp(int index) {
		index = size;
		while (hasParent(index) && (((Comparable<T>) arr[parentIdx(index)]).compareTo(arr[index]) < 0))
		{
			swap(index, parentIdx(index));
			index = parentIdx(index);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void bubbleDown() {
		int index = 1;
		while (hasLeftChild(index)) {
			// See which of children is smaller.
			int child = leftChild(index);
			// Bubble with the smaller child, if I have a smaller child
			if (hasRightChild(index)
					&& ((Comparable<T>) arr[leftChild(index)]).compareTo(arr[rightChild(index)]) < 0) {
				child = rightChild(index);
			} 

			if (((Comparable<T>) arr[index]).compareTo(arr[child]) < 0) {
				swap(index, child);
			} else {

				break;
			}
			// Update loop counter/index of where last element is put.
			index = child;
		}
	}
	
	public void printList() {
		System.out.println("\nArray size = " + size);
	    for (int i = 1; i < size + 1; i++ ) {
	    	System.out.println("Heap: " + arr[i]);
	    }
	  }

	@Override
	public T peek() {
		return arr[1];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int compareTo(T o) {
		return 0;
	}

	@Override
	public int size() {
		return size;
	}
}
