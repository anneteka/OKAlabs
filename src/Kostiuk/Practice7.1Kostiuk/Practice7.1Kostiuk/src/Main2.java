import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.omg.CosNaming.IstringHelper;

public class Main2 {
  static Scanner in;
  
  public static void main(String[] args) {
    input();
  }
  
  // method that receives input data
  static void input() {
    
    
     in = new Scanner(System.in);
    while (in.hasNextLine()) {
      try {
        
        String[] str=in.nextLine().split(" ");
        int n=str.length;
        if(isInRangeN(n)==false) {
          System.out.println("Wrong number of guests");
          return;
        }
      MaxPQ<Integer> inputNum = new MaxPQ(n);
      try {
        for (int i=0; i<str.length; i++) {
          
            Integer in = Integer.parseInt(str[i]);
            if (isInRangeTalk(in)==false) {
              System.out.println("Wrong number of people a guest wants to talk to");
              return;
            }
            inputNum.insert(in);
          
//                
        }
      }
      catch(NumberFormatException e) {
        
        System.out.println("Input is not a digit");
        System.out.println();
        continue;
        
      }
        System.out.println(isHappy(inputNum, n));
        System.out.println();
      }
          
      catch (Exception e){
        e.getMessage();
        e.printStackTrace();
        System.out.println("Wrong input");
      }
    }
  return;
    
        
  }


  // methods that check whether input data is in range
  private static boolean isInRangeN(int n) {
    if(n>0&&n<=10000)
      return true;
    return false;
  }
  private static boolean isInRangeTalk(int a) {
    if(a>0&&a<=1000)
      return true;
    return false;
  }
//method checks if all guests are happy using MaxPriorityQueue
  static String isHappy (MaxPQ<Integer> numbers, int n) {
    while (!numbers.isEmpty()) {
    Integer max = numbers.delMax();
    ArrayList<Integer> temp=new ArrayList<>();
    while (max>0) {
      max--;
      if (numbers.isEmpty())
        return "fail";
      Integer reduce=numbers.delMax();
      
      if (reduce-1>0)
      temp.add(reduce-1);
    }
    for (int i=0; i<temp.size(); i++)
      
        numbers.insert(temp.get(i));
  
    }
    return "ok";
    
}
  
}


class MaxPQ<Key extends Comparable<Key>> {

  private Key[] pq;
  private int n;
  
  public MaxPQ(int capacity){
    pq = (Key[]) new Comparable[capacity+1];
  }
  
  public boolean isEmpty(){
    return n==0;
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