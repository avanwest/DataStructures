
import java.util.Set;
import java.util.TreeSet;

public class LinkedMap<K,V> implements Map<K,V> {

    LinkedList<K,V> list;

    public LinkedMap() {
        list = new LinkedList();
    }

    @Override
    public void put(K key, V value) throws Exception {
    		// Create node for insertion, iterate and throw exception if already exists.
        Node node = new Node<Object, Object>(key, value);
        for (int i = 0; i < list.size(); i++ ) {
            if (list.get(i).equals(node)) {
                throw new Exception("Duplicate entry!");
            }
        }
        // Insert at beginning. 
        list.enqueue(node);
    }


    @Override
    public V get(K key) {
    		// Iterate over the list and return the key, otherwise return null.
        for (int i = 0; i < list.size(); i++ ) {
            Node<K,V> node = list.get(i);
            if (node != null && node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    public Set<K> getKeys() {
        TreeSet<K> set = new TreeSet();
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i).getKey());
        }
        return set;
    }

    @Override
    public V remove(K key) {
        return list.remove(key);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return (list.size() == 0);
    }

    @Override
    public String toString() {
        return "LinkedMap{" +
                "list=" + list +
                '}';
    }

    public String getType() {
        return "LinkedMap";
    }
}
