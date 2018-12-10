/**
 * 
 * 
 * Сортування злиттям
 * У зв'язку з модернізацією виробництва на заводі зубних щіток у Тау Кита було вирішено переписати список роботів, які обслуговують завод.
 * Кожен робот має два номери: основний та допоміжний. Новий список повинен задовільняти наступним правилам:
 * 
 * Якщо один робот у новому списку знаходиться раніше іншого, то основний номер першого менше або дорівнює основному номеру другого.
 * Якщо основні номери роботів рівні, то вони розміщені у такому ж порядку, як і у заданому списку.
 * Тау Китяни звернулись до Вас з проханням переписати список. Допоможіть модернізації організацій!
 * 
 * Вхідні дані
 * У першому рядку вхідного файлу знаходиться число N (1 ≤ N≤ 100000) - кількість роботів на заводі.
 * У кожному наступному рядку знаходяться 2 числа - основний та допоміжний номери чергового робота.
 * Обидва номери невід'ємні і не перевищують 109.
 * 
 * Вихідні дані
 * Виведіть N рядків, i-ий містить 2 числа - основний та допоміжний номер i-го робота у новому списку.
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class main {

  static class Key implements Comparable<Key> {
    int i, j, k;
    Key(int i, int j, int k) {
      this.i = i; this.j = j; this.k = k;
    }

    @Override
    public int compareTo(Key o) {
      int c = this.i - o.i;
      return c == 0 ? this.k - o.k : c;
    }
  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	  int n = Integer.valueOf(in.readLine()); 
    Key ns[] = new Key[n];
    
    for(int i=0; i<n; i++) {
    	String line = in.readLine();
    	StringTokenizer st= new StringTokenizer(line);
    	ns[i] = new Key(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()), i);
    }
    Arrays.sort(ns);
    for(Key k : ns) System.out.println(k.i + " " + k.j);
  }
}