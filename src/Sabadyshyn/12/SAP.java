import edu.princeton.cs.algs4.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SAP {

    private Digraph G;

    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

    private Map<Integer, Integer> getAncestors(int v) {
        Queue<Integer> vQ = new Queue<Integer>();
        Map<Integer, Integer> vM = new HashMap<Integer, Integer>();
        vQ.enqueue(v);
        vM.put(v, 0);
        while (!vQ.isEmpty()) {
            int head = vQ.dequeue();
            int currlng = vM.get(head);
            for (Integer w : G.adj(head)) {
                if (!vM.containsKey(w) || vM.get(w) > currlng + 1) {
                    vQ.enqueue(w);
                    vM.put(w, currlng + 1);
                }
            }
        }
        return vM;
    }

    public int length(int v, int w) {
        if (v < 0 || v >= G.V())
            throw new IndexOutOfBoundsException();
        if (w < 0 || w >= G.V())
            throw new IndexOutOfBoundsException();
        Map<Integer, Integer> ancestorV = getAncestors(v);
        Map<Integer, Integer> ancestorW = getAncestors(w);
        int dist = -1;
        for (Entry<Integer, Integer> items : ancestorV.entrySet()) {
            if (ancestorW.containsKey(items.getKey())) {
                int currentDist = ancestorW.get(items.getKey())
                        + items.getValue();
                if (dist < 0 || currentDist < dist)
                    dist = currentDist;
            }
        }
        return dist;
    }

    public int ancestor(int v, int w) {
        Map<Integer, Integer> ancestorV = getAncestors(v);
        Map<Integer, Integer> ancestorW = getAncestors(w);
        if (v < 0 || v >= G.V())
            throw new IndexOutOfBoundsException();
        if (w < 0 || w >= G.V())
            throw new IndexOutOfBoundsException();
        int dist = -1, anc = -1;
        for (Entry<Integer, Integer> items : ancestorV.entrySet()) {
            if (ancestorW.containsKey(items.getKey())) {
                int currentDist = ancestorW.get(items.getKey())
                        + items.getValue();
                if (dist < 0 || currentDist < dist) {
                    dist = currentDist;
                    anc = items.getKey();
                }
            }
        }
        return anc;
    }


    public int length(Iterable<Integer> v, Iterable<Integer> w)     // довжина найближчого шляху між v тa v. -1 якщо шляху немає
            throws IndexOutOfBoundsException {
        int dist = -1;
        for (Integer eV : v) {
            for (Integer eW : w) {
                int currentDist = length(eV, eW);
                if (currentDist > 0 && (dist < 0 || currentDist < dist))
                    dist = currentDist;
            }
        }
        return dist;
    }


    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)     // спільний предок через рекурсію
            throws IndexOutOfBoundsException {
        int crrLng;
        int lng = -1;
        int ancestor = -1;

        for (Integer eV : v) {
            for (Integer eW : w) {
                crrLng = length(eV, eW);
                if ((crrLng < lng || lng < 0) && crrLng > 0) {
                    lng = crrLng;
                    ancestor = ancestor(eV, eW);
                }
            }
        }
        return ancestor;
    }

    public static void main(String[] args) throws FileNotFoundException {     //тестер з презентації видозмінений та реалізований через Scanner
        File f = new File("input/digraph1.txt");
        Scanner sc = new Scanner(f);
        Digraph G = new Digraph(sc);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

}