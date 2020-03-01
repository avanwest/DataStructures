import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;



public class Main {

	private static final int taskCnt = 100000;
	private static List<Task> tasks = new ArrayList<Task>();
	private static Random ran = new Random();
	private static long llEnTime;
	private static long arrEnTime;
	private static long heapEnTime;
	private static long llDeqTime;
	private static long arrDeqTime;
	private static long heapDeqTime;
	private static long llEnDeqTime;
	private static long arrEnDeqTime;
	private static long heapEnDeqTime;
	
	public static void main(String[] args) {
		
		long llEnTotal = 0;
		long llDeqTotal = 0;
		long llEnDeqTotal = 0;
		long arrEnTotal = 0;
		long arrDeqTotal = 0;
		long arrEnDeqTotal = 0;
		long heapEnTotal = 0; 
		long heapDeqTotal = 0; 
		long heapEnDeqTotal = 0;
		
		int testRun = 1;
		while (testRun <= 10) {
			System.out.println("TestRun: " + testRun);
		
		for (int i = 0; i < taskCnt; i++) {
			tasks.add(new Task(i, ran.nextInt(10000000)));
		}
		
		System.out.println("\nLinkedList: ");
		testSorting(new LinkedList<Task>(), tasks, 20);
		System.out.println("\nDynamicArray: ");
		testSorting(new DynamicArray<Task>(Task.class, 25), tasks, 20);
		System.out.println("\nHeapQueue: ");
		testSorting(new Heap<Task>(Task.class, 25), tasks, 20);
		System.out.println("\n ");
		
		
		timeLinkedList(tasks);
		llEnTotal += llEnTime;
		llDeqTotal += llDeqTime;
		llEnDeqTotal += llEnDeqTime;
		
		timeDynamicArray(tasks);
		arrEnTotal += arrEnTime;
		arrDeqTotal += arrDeqTime;
		arrEnDeqTotal += arrEnDeqTime;
		
		timeHeapQueue(tasks);
		heapEnTotal += heapEnTime;
		heapDeqTotal += heapDeqTime;
		heapEnDeqTotal += heapEnDeqTime;
		
		testRun++; 
		}
		System.out.println("LinkList Averages for " + taskCnt + " items");
		System.out.println("------------------------------------------");
		System.out.println("Avg. Enqueue time: " + averageTime(llEnTotal, 10));
		System.out.println("Avg. Dequeue time: " + averageTime(llDeqTotal, 10));
		System.out.println("Avg. Enqueue/Dequeue time: " + averageTime(llEnDeqTotal, 10));
		System.out.println(" ");
		System.out.println("DynamicArray Averages for " + taskCnt + " items");
		System.out.println("-------------------------------------------");
		System.out.println("Avg. Enqueue time: " + averageTime(arrEnTotal, 10));
		System.out.println("Avg. Dequeue time: " + averageTime(arrDeqTotal, 10));
		System.out.println("Avg. Enqueue/Dequeue time: " + averageTime(arrEnDeqTotal, 10));
		System.out.println(" ");
		System.out.println("Heap Averages for " + taskCnt + " items");
		System.out.println("-------------------------------------------");
		System.out.println("Avg. Enqueue time: " + averageTime(heapEnTotal, 10));
		System.out.println("Avg. Dequeue time: " + averageTime(heapDeqTotal, 10));
		System.out.println("Avg. Enqueue/Dequeue time: " + averageTime(heapEnDeqTotal, 10));
		System.out.println(" ");
		
	}
	
	@SuppressWarnings("unchecked")
	private static <T> void testSorting(PriorityQueue<T> queue, List<Task> tasks, int cnt) {
		
		for (int i = 0; i < cnt; i++ ) {
			queue.enqueue((T) tasks.get(i));
		}
		for (int i = 0; i < cnt; i++ ) {
			System.out.println(queue.dequeue());
		}
	}
	
	private static void timeLinkedList(List<Task> tasks) {
		System.out.println("Test LinkedList");
		LinkedList<Task> linkedList = new LinkedList<>();
		long t = System.currentTimeMillis();
		for (int i = 0; i < taskCnt; i++) {
			linkedList.enqueue(tasks.get(i));
		}
		llEnTime = System.currentTimeMillis() - t;
		System.out.println("Enqueue " + taskCnt + " items: " + llEnTime + " milliseconds");
		
		t = System.currentTimeMillis();
		for (int i = 0; i < taskCnt; i++) {
			linkedList.dequeue();

		}
		llDeqTime = System.currentTimeMillis() - t;
		System.out.println("Dequeue " + taskCnt + " items: " + llDeqTime + " milliseconds");
		
		linkedList = new LinkedList<>();
		t = System.currentTimeMillis();
		int j = 0;
		while ( j < tasks.size() ) {
			if ( ran.nextInt() % 2 == 0) {
				linkedList.enqueue(tasks.get(j));
				j++;
			}
			else {
				linkedList.dequeue();
			}
		}
		llEnDeqTime = System.currentTimeMillis() - t;
		System.out.println("Enqueue/Dequeue " + taskCnt + " items: " + llEnDeqTime + " milliseconds\n");
	}
	
	private static void timeDynamicArray(List<Task> tasks) {
		System.out.println("Test DynamicArray");
		DynamicArray<Task> dynArray =  new DynamicArray<>(Task.class, tasks.size() + 1);
		long t = System.currentTimeMillis();
		for (int i = 0; i < taskCnt; i++) {
			dynArray.enqueue(tasks.get(i));
		}
		arrEnTime = System.currentTimeMillis() - t;
		System.out.println("Enqueue " + taskCnt + " items: " + arrEnTime + " milliseconds");
		
		for (int i = 0; i < taskCnt; i++) {
			dynArray.dequeue();
		}
		arrDeqTime = System.currentTimeMillis() - t;
		System.out.println("Dequeue " + taskCnt + " items: " + arrDeqTime + " milliseconds");
				
		dynArray = new DynamicArray<>(Task.class, tasks.size() + 1);
		t = System.currentTimeMillis();
		int j = 0;
		while ( j < tasks.size() ) {
			if ( ran.nextInt() % 2 == 0) {
				dynArray.enqueue(tasks.get(j));
				j++;
			}
			else {
				dynArray.dequeue();
				
			}
		}
		arrEnDeqTime = System.currentTimeMillis() - t;
		System.out.println("Enqueue/Dequeue " + taskCnt + " items: " + arrEnDeqTime + " milliseconds\n");

	}
	
	private static void timeHeapQueue(List<Task> tasks) {
		System.out.println("Test HeapQueue");
		Heap<Task> heap =  new Heap<>(Task.class, tasks.size() + 1);
		long t = System.currentTimeMillis();
		for (int i = 0; i < taskCnt; i++) {
			heap.enqueue(tasks.get(i));
		}
		heapEnTime = System.currentTimeMillis() - t;
		System.out.println("Enqueue " + taskCnt + " items: " + heapEnTime + " milliseconds");
		
		t = System.currentTimeMillis();
		for (int i = 0; i < taskCnt; i++) {
			heap.dequeue();
		}
		heapDeqTime = System.currentTimeMillis() - t;
		System.out.println("Dequeue " + taskCnt + " items: " + heapDeqTime + " milliseconds");
		
		heap =  new Heap<>(Task.class, tasks.size() + 1);
		t = System.currentTimeMillis();
		int j = 0;
		while ( j < tasks.size() ) {
			if ( ran.nextInt() % 2 == 0) {
				heap.enqueue(tasks.get(j));
				j++;
			}
			else {
				heap.dequeue();
			}
		}
		heapEnDeqTime = System.currentTimeMillis() - t;
		System.out.println("Enqueue/Dequeue " + taskCnt + " items: " + heapEnDeqTime + " milliseconds\n");

	}
	
	public static int averageTime(long totalTime, int numRuns) {
		int average = (int)totalTime/numRuns;
		return average;
	}
	
	public static <T> void writeToCSV(int testSize, PriorityQueue<T> queue, long time, String fName) {
		try {
		PrintWriter pw = new PrintWriter(fName);
		pw.println(testSize);
		} catch (FileNotFoundException fnfe) {
			System.out.print("Failed to Open file "+ fName);
		}
	}
}

