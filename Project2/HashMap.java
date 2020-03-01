
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class HashMap<K,V> implements Map<K,V> {

    private static final int MAX_LOAD_FACTOR = 20; // Amount of values per bucket.
    private int bucketSize = 64;
    private LinkedMap<K,V>[] buckets;
    private int size;


    public HashMap() {
    		// Initialize HashMap with LinkedMap in each bucket.
        buckets = new LinkedMap[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedMap<K, V>();
        }
    }

    @Override
    public void put(K key, V value) throws Exception {
        if (key == null) {
            throw new Exception("Null keys are not allowed.");
        }
        int index = key.hashCode() & (bucketSize - 1);
        // If the load factor is reached, regrow our buckets.
        if (buckets[index].size() >= MAX_LOAD_FACTOR) {
            addBuckets();
            // Truncate hashCode to appropriate size. 
            index = key.hashCode() & (bucketSize - 1); 
        }
        // Call put on LinkedMap and increase size. 
        buckets[index].put(key, value);
        size++;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        // Truncate hashCode to appropriate size. 
        int index = key.hashCode() & (bucketSize - 1);
        // Call get on LinkedMap
        return buckets[index].get(key);
    }

    // Found convienence method used to get all keys. 
    public Set<K> getKeys() {
        TreeSet<K> set = new TreeSet();
        for (LinkedMap item : buckets) {
            set.addAll(item.getKeys());
        }
        return set;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        // Truncate hashCode to index into available buckets. 
        int index = key.hashCode() & (bucketSize - 1);
        // Call LinkedMap remove method. 
        V value = buckets[index].remove(key);
        // If not null then we removed something so decrement size. 
        if (value != null) {
            size--;
        }
        return value;
    }

    private void addBuckets() {
    		// Double the bucketSize.
        int newBucketSize = bucketSize * 2;
        // Create a new LinkedMap with new size and initialize.  
        LinkedMap[] newBuckets = new LinkedMap[newBucketSize];
        for (int i = 0; i < newBucketSize; i++) {
            newBuckets[i] = new LinkedMap<K,V>();
        }
        // Transfer all the data from old LinkedMap to new one. 
        for (int i = 0; i < bucketSize; i++) {
            LinkedMap lmap = buckets[i];
            // Get all the keys in this LinkedMap. 
            Set keys = lmap.getKeys();
            for (Object key : keys) {
            		// Re-truncate hashCode for new buckets
                int index = key.hashCode() & (newBucketSize -1);
                try {
                    newBuckets[index].put(key, lmap.get(key));
                } catch (Exception e) {
                    System.out.println("Error adding key to new bucket list, key: " + key);
                }
            }
        }
        // Update the bucket variable and its size.
        buckets = newBuckets;
        bucketSize = newBucketSize;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "bucketSize=" + bucketSize +
                ", buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                '}';
    }

    public String getType() {
        return "HashMap";
    }
}
