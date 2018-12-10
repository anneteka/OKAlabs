import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    int N = 0;
    int n = 0;

    public Main() {

    }

    public static void main(String[] args) throws IOException {
        Main pb = new Main();
        pb.start();
    }


    private void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(bf.readLine());


        for(int o = 0; o < N; o++) {
            n = Integer.parseInt(bf.readLine());
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