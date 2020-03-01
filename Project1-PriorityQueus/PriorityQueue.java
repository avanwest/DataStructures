
public interface PriorityQueue<T> extends Comparable<T> {
	
	void enqueue(T item);
	T dequeue();
	T peek();
	int size();
	boolean isEmpty();
}
