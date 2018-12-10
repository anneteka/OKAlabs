import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Trie {
	static class TrieNode {
		Map<Character, TrieNode> children = new TreeMap<Character, TrieNode>();
		boolean leaf;
	}

	TrieNode root = new TrieNode();

	public void put(String s) {
		TrieNode v = root;
		for (char ch : s.toLowerCase().toCharArray()) {
			if (!v.children.containsKey(ch)) {
				v.children.put(ch, new TrieNode());
			}
			v = v.children.get(ch);
		}
		v.leaf = true;
	}

	public boolean find(String s) {
		TrieNode v = root;
		for (char ch : s.toLowerCase().toCharArray()) {
			if (!v.children.containsKey(ch)) {
				return false;
			} else {
				v = v.children.get(ch);
			}
		}
		return true;
	}

	static Map<Integer, String> levelSpacesMap = new HashMap<Integer, String>();

	static String getSpace(int level) {
		String result = levelSpacesMap.get(level);
		if (result == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < level; i++) {
				sb.append(" ");
			}
			result = sb.toString();
			levelSpacesMap.put(level, result);
		}
		return result;
	}

	public static void printSorted(TrieNode node) {
		printSorted2(node, 0);
	}

	private static void printSorted2(TrieNode node, int level) {
		for (Character ch : node.children.keySet()) {
			System.out.println(getSpace(level) + ch);
			printSorted2(node.children.get(ch), level + 1);
		}
		if (node.leaf) {
			System.out.println();
		}
	}
}