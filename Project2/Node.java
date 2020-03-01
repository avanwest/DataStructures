
import java.util.Objects;

public class Node<K, V> {

    private K key;
    private V value;
    private Node<K, V> next;


    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }


    public Node<K, V> getNext() {
        return next;
    }


    public void setNext(Node<K, V> next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "Node{" +
                "key='" + key + '\'' +
                ", value='" + value + "'" +
                ", next=" + next +
                '}';
    }

    @Override
    // Auto-generated equals method by IDE
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return Objects.equals(key, node.key) &&
                Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key, value);
    }



}
