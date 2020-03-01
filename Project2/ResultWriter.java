
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResultWriter<K,V> {
	
	// To store values. 
    private HashMap<String,List<Long>> insertMap;
    private HashMap<String,List<Long>> removeMap;

	public ResultWriter() {
        insertMap = new HashMap<String, List<Long>>();
        removeMap = new HashMap<String, List<Long>>();
	}

	public void addToInsert(String type, Integer samples, Long time) {
		
	    String key = type + "_" + samples;
	    if (insertMap.get(key) == null) {
            try {
            		// Create a new entry and add an ArrayList to the HashMap for that key. 
                insertMap.put(key, new ArrayList<Long>());
            } catch (Exception e) {
                System.out.println("ResultWriter - unexpected duplicate!");
            }
        }
	    // Get the list for the key and add the time to the list. 
	    List<Long> list = insertMap.get(key);
	    list.add(time);
    }
	
	// Same notes as for addToInsert
    public void addToRemove(String type, Integer samples, Long time) {
        String key = type + "_" + samples;
        if ( removeMap.get(key) == null ) {
            try {
                removeMap.put(key, new ArrayList<Long>());
            } catch (Exception e) {
                System.out.println("ResultWriter - unexpected duplicate!");
            }
        }
        List<Long> list = removeMap.get(key);
        list.add(time);
    }


    public  void writeToCSV(String file) {
		try {
		PrintWriter pw = new PrintWriter(file);
		pw.println("Inserts");
		pw.println("Test Size, LinkedMap, HashMap, BSTMap, AVLMap");
        Set<String> set = this.insertMap.getKeys();
        for (String key : set) {
            List<Long> timesList = this.insertMap.get(key);
            pw.println(key + ", " + timesList.get(0) + ", " + timesList.get(1) + ", " + timesList.get(2) + ", " + timesList.get(3));
        }
        pw.println("");

        pw.println("Removes");
        pw.println("Test Size, LinkedMap, HashMap, BSTMap, AVLMap");
        set = this.removeMap.getKeys();
        for (String key : set) {
            List<Long> timesList = this.removeMap.get(key);
            pw.println(key + ", " + timesList.get(0) + ", " + timesList.get(1) + ", " + timesList.get(2) + ", " + timesList.get(3));
        }

		pw.flush();
		pw.close();
		
		} catch(FileNotFoundException fnfe) {
			System.out.println("Failed to open the file " + file);
		}
		
	}

}
