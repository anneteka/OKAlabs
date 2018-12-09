import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DoctorWho {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (in.ready()) {
                String[] s = in.readLine().split(" ");
                int[] values = new int[s.length];
                for (int i = 0; i < values.length; i++) {
                    values[i] = Integer.parseInt(s[i]);
                }

                System.out.println(calculate(values) ? "ok" : "fail");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean calculate(int[] arr) {
        MaxPQ<Integer> pq = new MaxPQ<>(arr.length);
        ArrayList<Integer> toAdd = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            pq.insert(arr[i]);
        }

        while (!pq.isEmpty()) {
            int top = pq.delMax();
            while (top-- > 0) {
                if (pq.isEmpty())
                    return false;
                int i = pq.delMax();
                if (i != 1)
                    toAdd.add(i - 1);
            }

            while (!toAdd.isEmpty()) {
                pq.insert(toAdd.remove(0));
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
        return n ==0;
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
        while (k>1 && more(k/2,k)){
            exch(k,k/2);
            k=k/2;
        }
    }

    private void sink(int k){
        while(2*k<=n){
            int j = 2*k;
            if (j<n&&more(j,j+1)) j++;
            if (!more(k,j)) break;
            exch(k,j);
            k=j;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private boolean more(int i, int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

}