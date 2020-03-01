
public interface Map<K, V> {

    void put(K key, V value) throws Exception;

    V get(K key);

    V remove(K key);

    int size();

    boolean isEmpty();

    String getType();
}
