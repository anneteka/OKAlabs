import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	int N = 0;
	int n = 0;
	
	class Node {
		private char value;
		private HashMap<Character, Node> leaf;
		private boolean end;
		
		public Node(char ch) {
			value = ch;
			end = false;
			leaf = new HashMap<>();
		}
		
		public char getValue() {
			return value;
		}

		public HashMap<Character, Node> getChildren() {
			return leaf;
		}

		public void setIsEnd(boolean val) {
			end = val;
		}

		public boolean isEnd() {
			return end;
		}
	}

	class Derevo {
		private boolean isCompatible = true;
		public Derevo() {
			node = new Node((char) 0);
		}

		public void insert(String word) {
			boolean isExtended = false;
			Node node = this.node;
			int length = word.length();

			for (int level = 0; level < length; level++) {
				HashMap<Character, Node> child = node.getChildren();
				char ch = word.charAt(level);

				if (child.containsKey(ch)) {
					if (child.get(ch).isEnd())
						isCompatible = false;
					node = child.get(ch);
				} 
				else{
					Node temp = new Node(ch);
					child.put(ch, temp);
					node = temp;
					isExtended = true;
				}
			}
			if (isCompatible && !isExtended)
				isCompatible = false;
			node.setIsEnd(true);
		}

		public boolean isCompatible() {
			return isCompatible;
		}

		private Node node;

	}

	class Reader {
		final private int bufSize = 1 << 16;
		
		private byte[] buffer;
		int pointer , bytes;
		
		private DataInputStream stream;

		public Reader() {
			stream = new DataInputStream(System.in);
			buffer = new byte[bufSize];
			pointer = bytes = 0;
		}
		
		

		public Reader(String file_name) throws IOException {
			stream = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[bufSize];
			pointer = bytes = 0;
		}
		
		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}
		
		public double nextDouble() throws IOException {
			double v = 1, res = 0;
			byte byt = read();
			while (byt <= ' ')
				byt = read();
			boolean neg = (byt == '-');
			if (neg)
				byt = read();
			
			res = res * 10 + byt - '0';
			while ((byt = read()) >= '0' && byt <= '9') {
				res = res * 10 + byt - '0';
			}

			if (byt == '.') {
				while ((byt = read()) >= '0' && byt <= '9') {
					res += (byt - '0') / (v *= 10);
				}
			}
			
			if (neg)
				return -res;
			return res;
		}
		
		public long nextLong() throws IOException {
			long result = 0;
			byte c = read();
			
			while (c <= ' ')
				c = read();
			
			boolean neg = (c == '-');
			
			if (neg)
				c = read();
			
			while ((c = read()) >= '0' && c <= '9'){
				result = result * 10 + c - '0';
			};
			
			if (neg)
				return -result;
			
			return result;
		}


		public String readLine() throws IOException {
			byte[] len = new byte[64];
			int n = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				len[n++] = (byte) c;
			}
			return new String(len, 0, n);
		}

		
		

		private void fillBuffer() throws IOException {
			bytes = stream.read(buffer, pointer = 0, bufSize);
			if (bytes == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (pointer == bytes)
				fillBuffer();
			return buffer[pointer++];
		}

		public void close() throws IOException {
			if (stream == null)
				return;
			stream.close();
		}
	}

	
	
	private static int readInt(InputStream in) throws IOException {
		int ret = 0;
		boolean dig = false;

		for (int c = 0; (c = in.read()) != -1;) {
			if (c >= '0' && c <= '9') {
				dig = true;
				ret = ret * 10 + c - '0';
			} else if (dig)
				break;
		}

		return ret;
	}

	private void start() throws IOException {

		Reader reader = new Reader();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		N = reader.nextInt();

		for (int n = 0; n < N; n++) {
			n = reader.nextInt();
			Derevo derevo = new Derevo();
			for (int i = 0; i < n; i++) {
				String str = reader.readLine();
				derevo.insert(str);
			}
			if (derevo.isCompatible()) {
				writer.write("YES\n");
				writer.flush();
			} else {
				writer.write("NO\n");
				writer.flush();
			}

		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println(" ");
		BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(System.out));

		Main pb = new Main();
		pb.start();
		// System.out.println(Integer.parseInt());
	}

	

}

