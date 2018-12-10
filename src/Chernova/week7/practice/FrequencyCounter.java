package Chernova.week7.practice;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class FrequencyCounter {
	
	private final static int minlen = 8; //мінімальна довжина слів, що розглядаються
	//private static final String testFile = "tinyTale.txt";
	private static final String testFile = "tale.txt";
	//private static final String testFile = "leipzig1M.txt";
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
	        int distinct = 0, words = 0;
	        
	        ST<String, Integer> st = new ST<String, Integer>();

			BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile));
			System.setIn(in);
	        
	        // compute frequency counts
	        while (!StdIn.isEmpty()) {
	            String key = StdIn.readString();
	            if (key.length() < minlen) continue;
	            words++;
	            if (st.contains(key)) { st.put(key, st.get(key) + 1); }
	            else                  { st.put(key, 1); distinct++; }
	        }

	        // find a key with the highest frequency count
	        String max = "";
	        st.put(max, 0);
	        for (String word : st.keys()) {
	            if (st.get(word) > st.get(max))
	                max = word;
	        }

	        StdOut.println(max + " " + st.get(max));
	        StdOut.println("distinct = " + distinct);
	        StdOut.println("words  = " + words);
	}
}
