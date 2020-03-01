
public class LinkedList<K, V> {
    private Node<K,V> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public Node<K,V> get(int idx) {
        Node<K,V> node = head;
        // Go to idx but don't overrun
        for (int i = 0; i < Math.min(idx, size()); i++) {
            node = node.getNext();
            if (node == null) break;
        }
        return node;
    }


    public void enqueue(Node<K,V> node) {
    		// If it's empty, insert at head.
        if (size == 0) {
            head = node;
            node.setNext(null);
        }
        // Otherwise insert at beginning and set next.
        else {
            node.setNext(head);
            head = node;
        }
        size++;
    }

    public V remove(K key) {
        Node<K,V> current = head;
        Node<K,V> previous = null;
        V value = null;
        if (current == null) return value;
        // Is the head node the one to remove?
        if (current.getKey().equals(key)) {
            value = head.getValue();
            head = head.getNext();
            size--;
            return value;
        }
        // Search the rest of the list.
        for (int i = 0; i < size(); i++) {
            previous = current;
            current = current.getNext();
            if (current == null) return value;
            
            // If the current equals the key then set pointer in previous to next one after current one. 
            if (current.getKey().equals(key)) {
                value = current.getValue();
                previous.setNext(current.getNext());
                size--;
                return value;
            }
        }
        return value;
    }

    public Node<K,V> dequeue() {
    		// Remove from the head, set the next one, and shrink size.
        Node h = head;
        head = head.getNext();
        size--;
        return h;
    }

    public Node<K,V> peek() {
        return head;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size > 0;
    }



    @Override
    public String toString() {
        return "LinkedList{" +
                "size=" + size +
                ", head=" + head +
                '}';
    }
}
