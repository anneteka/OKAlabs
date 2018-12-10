import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Maria Skyrta. E-olymp List of phone numbers. Check if the a number
 *         from list is a prefix of another number. Algorithm: insert all
 *         numbers to prefix-tree structure. Consider numbers incompatible if
 *         the node in which number ends has more outcoming nodes or if another
 *         number ends in a node of this number.
 *
 */

public class PhoneNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int t = Integer.parseInt(s);
		for (int i = 0; i < t; i++) {
			s = br.readLine();
			int n = Integer.parseInt(s);
			boolean compatible = true;
			Trie trie = new Trie();
			for (int j = 0; j < n; j++) {
				s = br.readLine();
				if (compatible) {
					compatible = trie.insert(s);
				}
			}
			if (compatible)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}

class Node {
	Node[] next;
	int outcoming;
	boolean endOf;

	Node() {
		next = new Node[10];
		outcoming = 0;
		endOf = false;
	}
}

class Trie {
	public Node root;

	Trie() {
		root = new Node();
	}

	boolean insert(String s) {
		// int n = 0;
		Node cur = root;
		for (int i = 0; i < s.length(); i++) {
			int let = s.charAt(i) - '0';
			if (cur.next[let] == null) {
				cur.next[let] = new Node();
				cur.outcoming++;
			}
			cur = cur.next[let];
			if (cur.endOf == true) {
				return false;
			}
			if (i == s.length() - 1) {
				cur.endOf = true;
				if (cur.outcoming != 0)
					return false;
			}
		}
		return true;

	}
}
