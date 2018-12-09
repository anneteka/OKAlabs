import java.util.ArrayList;
 import java.util.Comparator;

 public class MinPQ<Key extends Comparable<Key>> {
     private Comparator<Key> comparator;
   protected Key[] pq;
   protected int n;

   public MinPQ() {
     this(100);
   }

   public MinPQ(int capacity){
     pq = (Key[]) new Comparable[capacity+1];
   }
   
   public boolean isEmpty(){
     return n == 0;
   }
   
   public void insert(Key key){
     pq[++n] = key;
     swim(n);
   }
   
   public Key delMin(){
     Key max = pq[1];
     exch(1,n--);
     sink(1);
     pq[n + 1] = null;
     return max;
   }
   
   private void swim(int k){
     while (k>1 && more(k/2,k)){
       exch(k,k/2);
       k=k/2;
     }
   }
   
   private void sink(int k){
     while(2 * k <= n){
       int j = 2 * k;
       if (j < n && more(j,j + 1))
           j++;
       if (!more(k, j))
           break;
       exch(k,j);
       k = j;
     }
   }

   private boolean more(int i, int j) {
       if (this.comparator != null) {
           return this.comparator.compare(pq[i], pq[j]) > 0;
         }
         return pq[i].compareTo(pq[j]) > 0;
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