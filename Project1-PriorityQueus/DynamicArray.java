
import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> implements PriorityQueue<T> {
	
	private T[] pQueue;
	private int size;
	private int next; // next available spot 
	private boolean dirty;
	private Class<T> classType;
	
	@SuppressWarnings("unchecked")
	public DynamicArray(Class<T> classType, int capacity) {
		pQueue = (T[])Array.newInstance(classType, capacity);
		this.classType = classType;
		this.size = capacity;
		this.next = 0;
	}
	
	@SuppressWarnings("unchecked")
	public void grow() {
		// Create a new array and copy contents of old array into it.
		T[] newPQ = (T[])Array.newInstance(classType, size * 2);
		for (int i = 0; i < size; i++) {
			newPQ[i] = pQueue[i];
		}
		pQueue = newPQ;
		this.size = this.size * 2;
	}
	
	public void enqueue(T item) {
		// Check if there is room. If not create a bigger array and add element to end. 
		if (this.next >= pQueue.length - 1) grow();
		pQueue[this.next++] = item;
		// Note the array is not sorted now. 
		dirty = true;
	}
	
	public T dequeue() {
		T max = null;
		// Sort the array if you've added elements. 
		if (dirty) {
			Arrays.sort(pQueue, 0, next);
		}
		if ( next > 0 ) {
			max = pQueue[next - 1];
			next--;
		}
		
		return max;
	}
	
	
	public void printQueue() {
		System.out.println("Queue: ");
		for ( int i = 0; i < next; i++ ) {
			System.out.println(pQueue[i]);
		}
	}
	

	@Override
	public int compareTo(T o) {
		return 0;
	}

	public T[] getpQueue() {
		return pQueue;
	}

	public int getSize() {
		return size;
	}

	public boolean isDirty() {
		return dirty;
	}

	public Class<T> getClassType() {
		return classType;
	}

	@Override
	public T peek() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	
	
}
