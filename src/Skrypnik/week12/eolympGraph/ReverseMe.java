package week12.eolympGraph;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ReverseMe {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/week12/eolympGraph/input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("src/week12/eolympGraph/output.txt"));
        int number = Integer.parseInt(br.readLine());
        Digraph digraph = new Digraph(number);
        for (int i = 0; i < number; i++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            while (st.hasMoreTokens())
                digraph.addEdge(i, Integer.parseInt(st.nextToken()) - 1);
        }
        br.close();

        Digraph reversedDigraph = digraph.reverse();
        pw.write(number + "\n");
        for (int i = 0; i < number; i++) {
            StringBuilder s = new StringBuilder();
            for (int w : reversedDigraph.adj(i)) {
                s.append(String.valueOf(w + 1)).append(" ");
            }
            s.reverse();
            String myS = s.toString();
            if (!myS.equals(""))
                myS = myS.substring(1);
            pw.write(String.valueOf(myS));
            pw.write("\n");
        }
        pw.close();
    }
}

class Digraph {

    private final int V;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public int degree(int v) {
        int degree = 0;
        for (int w : adj(v))
            degree++;
        return degree;
    }

    public int V() {
        return V;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }
}

class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

