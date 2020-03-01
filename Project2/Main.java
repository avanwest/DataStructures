
import java.util.Random;
import java.util.Vector;

public class Main {

    private static final int KEY_COUNT = 1000;



    public static void main(String[] args) {
        ResultWriter<String, Integer> writer = new ResultWriter<>();
        int count = KEY_COUNT;
        for ( int i = 0; i < 3; i++) {

            // Test with random data.
        		System.out.println("Random Data Tests " + count);
        		System.out.println("------------------------------");
            test(new LoadGenerator<String, Integer>(count, true).generateString(), "Random", writer);
            System.out.println("------------------------------");

            // Test with non-random data.
            System.out.println("Sorted Data Tests " + count);
    			System.out.println("------------------------------");
            test(new LoadGenerator<String, Integer>(count, false).generateString(), "Sorted", writer);
            count = count += 1000;
            System.out.println("------------------------------");
        }

        writer.writeToCSV("./MapData.csv");

    }

    private static void test(Vector keys, String type, ResultWriter writer) {
        BenchMark bench = new BenchMark<String, Integer>(new LinkedMap<String, Integer>(), keys, type, writer);
        bench = new BenchMark<String, Integer>(new HashMap<String, Integer>(), keys, type, writer);
        bench = new BenchMark<String, Integer>(new BSTMap<String, Integer>(), keys, type, writer);
        bench = new BenchMark<String, Integer>(new AVLMap<String, Integer>(), keys, type, writer);
    }



}
