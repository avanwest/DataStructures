
public class Node<T> {
	
	private T item;
	private Node<T> next;
	private Node<T> prev;

	public Node(T value) {
		this.item = value;
	}
	
	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "Node [item:" + item + ", next:" + next + ", prev:" + prev + "]";
	}
	
}
