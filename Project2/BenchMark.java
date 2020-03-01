
import java.util.Vector;

public class BenchMark<K,V> {


	private Vector<K> keys;
	private Map<K,V> map;
	private ResultWriter<K,V> writer;
	private String keyType;
	
	public BenchMark(Map<K,V> map, Vector<K> keys, String keyType, ResultWriter<K,V> writer) {
	    this.map = map;
	    this.keys = keys;
	    this.writer = writer;
	    this.keyType = keyType;
	    System.out.println("Start insert test for " + map.getType() + " with " + keys.size() + " keys...");
        timeToInsert();
        System.out.println("Start remove test for " + map.getType() + " with " + keys.size() + " keys...");
        timeToRemove();

	}

	public void setWriter(ResultWriter<K,V> writer) {
	    this.writer = writer;
    }
	
	public  void timeToInsert() {
		// Set timer and loop over all keys. 
		long t = System.nanoTime();
		for (int i = 0; i < keys.size(); i++) {
			// Get key from vector, put in value based on loop counter. 
            try {
            		// Call put adding key and value. 
                map.put(keys.get(i), (V)(new Integer(i)));
            } catch (Exception e) {
                System.out.println("Duplicates are not allowed, key: " + keys.get(i));
            }
        }
		// Stop timer.
		long t2 = System.nanoTime() - t;
        System.out.println("Insert time: " + convertToSeconds(t2));
        
        // Add results to writer. 
        writer.addToInsert(keyType, keys.size(), t2);
	}
	
	public  void timeToRemove() {
		// identical to timeToInsert just removing half of the keys.  
        long t = System.nanoTime();
        for (int i = 0; i < keys.size()/2; i++) {
                map.remove(keys.get(i));
        }

        long t2 = System.nanoTime() - t;
        System.out.println("Remove time: " + convertToSeconds(t2));
        writer.addToRemove(keyType, keys.size()/2, t2);
    }

	// Makes output on screen easier to read. 
	// Writer still sends nano seconds. 
    private double convertToSeconds(long time) {
	    return (time / 1000000000.0);
    }
}
