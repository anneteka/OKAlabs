package week7.eolympDoctorWho;

import java.io.*;
import java.util.*;

public class Tester {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/week7/eolympDoctorWho/input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("src/week7/eolympDoctorWho/output.txt"));
        String s;
        StringTokenizer st1, st2;
        while (true) {
            s = br.readLine();
            if (s == null)
                break;
            st1 = new StringTokenizer(s);
            int n = 0;
            while (st1.hasMoreTokens()) {
                n++;
                st1.nextToken();
            }
            st2 = new StringTokenizer(s);

            MaxPQ<Integer> pq = new MaxPQ<>(n);
            while (st2.hasMoreTokens()) {
                pq.insert(Integer.parseInt(st2.nextToken()));
            }

            if (satisfied(pq))
                pw.write("ok\n\n");
            else
                pw.write("fail\n\n");
        }
        br.close();
        pw.close();
    }

    private static boolean satisfied(MaxPQ<Integer> pq) {
        while (!pq.isEmpty()) {
            int head = pq.delMax();
            ArrayList<Integer> al = new ArrayList<>();
            while (head > 0) {
                if (pq.isEmpty())
                    return false;
                int x = pq.delMax() - 1;
                al.add(x);
                head--;
            }
            Iterator<Integer> iterator = al.iterator();
            while (iterator.hasNext()) {
                int test = iterator.next();
                if (test != 0)
                    pq.insert(test);
            }


        }
        return true;
    }
}

class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n;

    public MaxPQ(int capacity){
        pq = (Key[]) new Comparable[capacity+1];
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public void insert(Key key){
        pq[++n] = key;
        swim(n);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1,n--);
        sink(1);
        pq[n+1]=null;
        return max;
    }

    private void swim(int k){
        while (k>1 && less(k/2,k)){
            exch(k,k/2);
            k=k/2;
        }
    }

    private void sink(int k){
        while(2*k<=n){
            int j = 2*k;
            if (j<n&&less(j,j+1)) j++;
            if (!less(k,j)) break;
            exch(k,j);
            k=j;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}

