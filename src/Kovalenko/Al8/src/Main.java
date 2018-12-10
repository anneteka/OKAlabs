
public class Main {
	 public static void main(String[] args) {
	        ST<String, Integer> st = new ST<String, Integer>();
	        for (int i = 0; i<7; i++) {
	            String key = StdIn.readString();
	            st.put(key, i);
	        }
	        for (String s : st.keys())
	            StdOut.println(s + " " + st.get(s));
	    }

}
