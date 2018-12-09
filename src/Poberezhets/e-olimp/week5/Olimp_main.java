package Practice4;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;


/**
 * (done)
 * У зв'язку з модернізацією виробництва на заводі зубних щіток у Тау Кита було
 * вирішено переписати список роботів, які обслуговують завод. Кожен робот має
 * два номери: основний та допоміжний. Новий список повинен задовільняти
 * наступним правилам:
 * 
 * Якщо один робот у новому списку знаходиться раніше іншого, то основний номер
 * першого менше або дорівнює основному номеру другого. Якщо основні номери
 * роботів рівні, то вони розміщені у такому ж порядку, як і у заданому списку.
 * Тау Китяни звернулись до Вас з проханням переписати список. Допоможіть
 * модернізації організацій!
 * 
 * Вхідні дані
 * 
 * У першому рядку вхідного файлу знаходиться число N (1 ≤ N≤ 100000) -
 * кількість роботів на заводі. У кожному наступному рядку знаходяться 2 числа -
 * основний та допоміжний номери чергового робота. Обидва номери невід'ємні і не
 * перевищують 109.
 * 
 * Вихідні дані
 * 
 * Виведіть N рядків, i-ий містить 2 числа - основний та допоміжний номер i-го
 * робота у новому списку.
 * 
 * @author Богдана
 *
 */
public class Olimp_main {
	private static final int CUTOFF = 7;

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int n1 = Integer.parseInt(r.readLine());
		int[] first = new int[n1];
		int[] second = new int[n1];
		for (int j = 0; j < n1; j++) {
			String numbers[] = r.readLine().split(" ");
			first[j] = Integer.parseInt(numbers[0]);
			second[j] = Integer.parseInt(numbers[1]);
		}
		sort(first, second);
		for(int i=0; i<n1;i++) 
			System.out.print(first[i]+" "+ second[i]+"\n");
	}

	private static void merge(int[] a, int[] aux, int[] b, int[] bux, int lo, int mid,
			int hi) {
		assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
		assert isSorted(a, mid + 1, hi); // precondition: a[mid+1..hi] sorted
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
			bux[k] = b[k];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j];
				b[k] = bux[j];
				j++;
			} else if (j > hi) {
				a[k] = aux[i];
				b[k] = bux[i];
				i++;
			} else if (aux[j]<aux[i]) {
				a[k] = aux[j];
				b[k] = bux[j];
				j++;
			} else {
				a[k] = aux[i];
				b[k] = bux[i];
				i++;
			}
		}
		assert isSorted(a, lo, hi); // postcondition: a[lo..hi] sorted
	}

	private static void sort(int[] a, int[] aux,int[] b, int[] bux, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux,b,bux, lo, mid);
		sort(a, aux,b,bux, mid+1, hi);
		merge(a, aux,b,bux, lo, mid, hi);

		
	}

	public static void sort(int[] a, int[] b) {
		int[] aux = new int[a.length];
		int[] bux = new int[b.length];
		sort(a, aux, b, bux, 0, a.length - 1);
	}

	private static boolean isSorted(int[] a, int l, int m) {
		for (int i = l; i <= m; i++)
			if (a[i]<a[i - 1])
				return false;
		return true;
	}

	

}


