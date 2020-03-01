import java.util.Random;
import java.util.Vector;

public class LoadGenerator<K,V> {
	
	private Vector<K> keys;
	private Random ran;
	private int size;
	private static final int MAX = 10000000;
	private boolean isRandom;
	

	public LoadGenerator(int size, boolean isRandom) {
		this.size = size;
		this.ran = new Random();
		this.keys = new Vector<K>(size);
		this.isRandom = isRandom;
	}
	

	public Vector<K> getKeys() {
		return keys;
	}
	
	public Vector<K> generateInteger() {
		for (int i = 0; i < this.size; i++) {
			// Generate random key.
			if (isRandom) {
				keys.add((K) new Integer(this.ran.nextInt(MAX)));
			}
			else {
				keys.add((K) new Integer(i));
			}
        }
        return keys;
	}

	public Vector<K> generateString() {
        for (int i = 0; i < this.size; i++) {
        	// Generate random string. 
        	if (isRandom) {
				keys.add((K) ("Key" + this.ran.nextInt(MAX)));
			}
			else {
				keys.add((K) ("Key" + i));
			}
        }
        return keys;
    }
	
}
