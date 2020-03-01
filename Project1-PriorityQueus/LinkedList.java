
public class LinkedList<T> implements PriorityQueue<T> {
	
	private Node<T> head;
	private int size;
	
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public void enqueue(T item) {
		Node<T> newNode = new Node<T>(item);
		// If list is empty, just add the node as the head.
		if (size == 0) {
			head = newNode;
			size++;
			return;
		}
		// Find the location to add the node.
		else {
			Node<T> current = head;
			// The new node is lower priority than the current node.
			int diff = ((Comparable<T>) newNode.getItem()).compareTo((T) current.getItem());
			if (diff < 0) {
				
				// While there is a next node in the list, and the new node is less than the next node, keep searching.
				// If the next node is null, then add the new node to the end of the list.
				// If the new node is greater than the next, then insert here.
				while ((current.getNext() != null) && (((Comparable<T>) newNode.getItem()).compareTo((T) current.getNext().getItem()) < 0)) {
						current = current.getNext();						
				}
				
				Node<T> next = current.getNext();
				
				// If we reached the end of the list, then add the new node to the end.
				if ( next == null ) {
					current.setNext(newNode);
					newNode.setPrev(current);
				}
				// Insert after the current.
				else {
					newNode.setPrev(current);
					newNode.setNext(next);
					next.setPrev(newNode);
					current.setNext(newNode);
				}	
			} 
			// The new node has a higher priority than the current/head node.
			else if (diff >= 0) {
				newNode.setNext(current);
				current.setPrev(newNode);
				head = newNode;
			} 
			size++;
		}
	}
	
	public T dequeue() {
		// If the there are elements in the list, the head is the highest priority.
		if (head != null) {
			Node<T> highest = head;
			head = head.getNext();
			size--;
			return highest.getItem();
		}
		else {
			return null;
		}
	}

	public T peek() {
		return head.getItem();
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size > 0;
	}
	
	@Override
	public int compareTo(T o) {
		return 0;
	}	
} 


