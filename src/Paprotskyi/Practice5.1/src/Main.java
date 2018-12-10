import java.io.*;

public class Main {

    static class Pair implements Comparable<Pair>{
        int first, second;

        Pair(int first, int second){
            this.first=first;
            this.second=second;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.first < o.first)
                return 1;
            return 0;
        }
    }

    private static void merge(Pair[] a, Pair[] aux, int lo, int mid, int hi){

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++){
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static void sort(Pair[] a, Pair[] aux, int lo, int hi){
        if (hi <= lo) return;
        else {int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid+1, hi);
            if (!less(a[mid+1],a[mid])) return;
            merge(a, aux, lo, mid, hi);
        }
    }

    public static void sort(Pair[] a)  {
        Pair[] aux = new Pair[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static boolean less(Pair v, Pair w){
        if (v.compareTo(w)==1)
            return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new FileReader(new File("input.txt")));
            pw = new PrintWriter(new File("output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = Integer.parseInt(br.readLine());
        Pair ns[] = new Pair[n];
        String s;

        for(int i=0; i<n; i++) {
            s = br.readLine();
            String[] sub;
            sub = s.split(" ");
            ns[i] = new Pair(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        sort(ns);

        for(Pair p : ns)
            pw.write(p.first + " " + p.second +"\n");
    pw.close();
    br.close();
    }
}