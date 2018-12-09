package pr13;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * У Байтландії є цілих n міст, але немає жодної дороги. Король країни,
 * Вольдемар де Беар, вирішив виправити цю ситуацію і з'єднати деякі міста
 * дорогами так, щоб по цим дорогам можно було дістатись від одного довільного
 * міста до довільного іншого. Коли будівництво буде завершено, король планує
 * відсвяткувати День Об'єднання.
 * 
 * На жаль, казна Байтландії поки що порожня, тому король вимагає зекономити
 * гроші, мінімізувавши сумарну довжину усіх побудованих доріг.
 * 
 * Вхідні дані Перший рядок містить кількість n (1 ≤ n ≤ 5000) міст у
 * Байтландії. Кожен з наступних n рядків містить по два цілих числа xi, yi -
 * координати i-го міста (-10000 ≤ xi, yi ≤ 10000). Жодні два міста не розміщені
 * в одній точці.
 * 
 * Вихідні дані Вивести мінімальну сумарну довжину доріг. Виведіть відповідь з
 * точністю не менше 10-3.
 */
public class Roads {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] xr = new int[n];
		int[] yr = new int[n];

		for (int i = 0; i < n; i++) {
			xr[i] = sc.nextInt();
			yr[i] = sc.nextInt();
		}

		int INF = Integer.MAX_VALUE;
		int[][] graph = new int[n][];

		for (int u = 0; u < n; u++) {
			graph[u] = new int[u + 1];
			graph[u][u] = 0;
			for (int v = 0; v < u; v++) {
				int dx = xr[u] - xr[v];
				int dy = yr[u] - yr[v];
				int w = dx * dx + dy * dy;
				graph[u][v] = w;
			}
		}
		boolean[] used = new boolean[n];// if this vertex is used
		int[] dist = new int[n];

		Arrays.fill(dist, INF);
		dist[0] = 0;

		while (true) {
			int v = -1;
			for (int nv = 0; nv < n; nv++)
				if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv]))
					v = nv;
			if (v == -1)
				break;
			used[v] = true;
			for (int nv = 0; nv < n; nv++)
				if (!used[nv] && get(graph, v, nv) < INF)
					dist[nv] = Math.min(dist[nv], get(graph, v, nv));
		}
		double w = 0;
		for (int d : dist) {
			w += Math.sqrt(d);
		}

		System.out.println(w);
	}

	// get element from triangle graph
	static int get(int[][] ar, int v, int u) {
		if (u > v)
			return ar[u][v];
		return ar[v][u];

	}
}
