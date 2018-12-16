
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneList {
  
	
	static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
	
	
	
	
  int N = 0;
  
  int n = 0;
  
  
  private static int readInt(InputStream in) throws IOException {
      int ret = 0;
      boolean dig = false;

      for (int c = 0; (c = in.read()) != -1; ) {
          if (c >= '0' && c <= '9') {
              dig = true;
              ret = ret * 10 + c - '0';
          } else if (dig) break;
      }

      return ret;
  }
  
  public static void main(String[] args) throws IOException {
	  System.out.println(" ");
      BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(System.out));
      
    PhoneList pb = new PhoneList();
    pb.start();
//    System.out.println(Integer.parseInt());
  }

  
  
  
  private void start() throws IOException {
	
	  Reader bf=new Reader();
	  
      BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(System.out));
	  
	  
      N = bf.nextInt();

    
    for(int o = 0; o < N; o++) {
        n = bf.nextInt();
      Trie numbers = new Trie();
      for(int i = 0; i < n; i++) {
        String str = bf.readLine();
        numbers.insert(str);
      }
      if(numbers.isCompatible()) {
          pw.write("YES" + "\n");
          pw.flush();
      }
      else {
          pw.write("NO" + "\n");
          pw.flush();
      }
      
    }
  }



}
class TrieNode {
    public TrieNode(char ch) {
        value = ch;
        children = new HashMap<>();
        bIsEnd = false;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public char getValue() {
        return value;
    }

    public void setIsEnd(boolean val) {
        bIsEnd = val;
    }

    public boolean isEnd() {
        return bIsEnd;
    }

    private char value;
    private HashMap<Character, TrieNode> children;
    private boolean bIsEnd;
}


class Trie {

    private boolean isCompatible = true;


    public Trie()   {
        root = new TrieNode((char)0);
    }


    public void insert(String word) {

        boolean wasExtended = false;

        int length = word.length();
        TrieNode crawl = root;

        // Traverse through all characters of given word
        for (int level = 0; level < length; level++) {
            HashMap<Character, TrieNode> child = crawl.getChildren();
            char ch = word.charAt(level);

            // If there is already a child for current character of given word
            if (child.containsKey(ch)) {
                if (child.get(ch).isEnd())
                    isCompatible = false;
                crawl = child.get(ch);
            } else // Else create a child
            {
                TrieNode temp = new TrieNode(ch);
                child.put(ch, temp);
                crawl = temp;
                wasExtended = true;
            }
        }
        if(isCompatible && !wasExtended)
            isCompatible = false;
        crawl.setIsEnd(true);
    }

    public boolean isCompatible() {
        return isCompatible;
    }

    private TrieNode root;
    
    
    
    
    
}