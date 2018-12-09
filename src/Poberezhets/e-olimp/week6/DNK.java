package pr5;

import java.util.Scanner;

import lib.In;

/**(DONE)
 * Перший рядок містить ціле число M, за яким йде пустий рядок і M тестів. Між
 * сусідніми тестами знаходиться пустий рядок.
 * 
 * Перший рядок кожного тесту містить два цілих числа: натуральне число n (0 < n
 * ≤ 50) - довжина вхідних рядків та натуральне число m (0 < m ≤ 100) -
 * кількість рядків. Далі йдет m рядків, кожен з яких має довжину n.
 * 
 * Вихідні дані
 * 
 * Для кожного тесту вивести послідовність рядків у порядку від "найбільш
 * відсортованої" до "найменш відсортованої". Якщо два або більше рядків рівні
 * при вказаному сортуванні, то виводити їх сліду у тому ж порядку у якому вони
 * поступали на вхід.
 * 
 * Між відповідями сусідні тести слід виводити пусти рядок.
 * 
 * @author Богдана
 *
 *         Перший рядок містить ціле число M, за яким йде пустий рядок і M
 *         тестів. Між сусідніми тестами знаходиться пустий рядок.
 * 
 *         Перший рядок кожного тесту містить два цілих числа: натуральне число
 *         n (0 < n ≤ 50) - довжина вхідних рядків та натуральне число m (0 < m
 *         ≤ 100) - кількість рядків. Далі йдет m рядків, кожен з яких має
 *         довжину n.
 * 
 * 
 * 
 */

public class DNK {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		int q=Integer.parseInt(str);
		for(int z=0; z<q;z++) {
			str=sc.nextLine();
			str=sc.nextLine();

			String numbers[] = str.split(" ");
			int m=Integer.parseInt(numbers[0]);
			int n=Integer.parseInt(numbers[1]);
			Integer[] dnk_int = new Integer[n];
			String[] dnk_string = new String[n];
	
			for (int i = 0; i < n; i++) {
				dnk_string[i] = sc.nextLine();
				dnk_int[i] = 0;
			}
			for (int i = 0; i < n; i++) {
				char[] arrayChar = dnk_string[i].toCharArray();
	
				for (int j = 0; j < arrayChar.length; j++) {
	
					for (int k = j; k < arrayChar.length; k++) {
						if (arrayChar[j] > arrayChar[k])
							dnk_int[i]++;
					}
				}
			}
			sort(dnk_int, dnk_string);
			for (String ss:dnk_string) {
				System.out.println(ss);
			}
			System.out.println();
		}

	}
	private static void exch(Comparable[] a,Comparable[] b, int i, int j){
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
		Comparable swap1 = b[i];
		b[i] = b[j];
		b[j] = swap1;
	}
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
	}
	
	private static boolean isSorted(Comparable[] a){
		for (int i=1;i<a.length;i++)
			if (less(a[i],a[i-1])) return false;
		return true;
	}

	public static void sort(Comparable[] a, Comparable[] b){
		int n = a.length;
		for (int i=0;i<n;i++){
			for (int j = i;j>0;j--)
				if (less(a[j],a[j-1])) {
					exch(a,b,j,j-1);
				}
				else break;
		}
	}
}
