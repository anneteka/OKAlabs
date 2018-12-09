package pr14;

import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Між кожною парою міст країни є пряма двостороння дорога. Петро хоче підірвати
 * таку кількість доріг, щоб утворилося хоча б два міста, проїзд між якими був
 * би неможливий.
 * 
 * Вам відома вартість підриву кожної дороги. Знайти найменшу вартість, за яку
 * Петру вдасться здійснити задумане.
 * 
 * Вхідні дані Складається з декількох тестів. Перший рядок кожного тесту
 * містить кількість n (n ≤ 50) міст у країні. Наступні n рядків описують
 * дороги: j-ий символ i-го рядка є цифрою, яка задає вартість знищення дороги,
 * що веде з i-го міста в j-ий.
 * 
 * Вихідні дані Для кожного тесту вивести в окремому рядку найменшу вартість, за
 * яку Петру вдасться здійснити задумане.
 * 
 * @author Богдана
 *
 */

public class DestroyRoad {
	static int INF = Integer.MAX_VALUE;
	static int[][] cap;
	static int[][] res;
	static boolean[] used;
	static int n;

	public static void main(String[] args) throws IOException {

		Scanner br = new Scanner(System.in);
		String line;
		while ((line = br.nextLine()) != null) {
			n = Integer.parseInt(line);
			cap = new int[n][n];
			res = new int[n][n];
			used = new boolean[n];
			for (int i = 0; i < n; i++) {
				line = br.nextLine();
				for (int j = 0; j < n; j++)
					cap[i][j] = line.charAt(j) - '0';
			}
			System.out.println(countRes());
		}

	}

	private static int countRes() {
		int best = INF;
		for (int s = 1; s < n; s++)
			best = Math.min(best, mincut(0, s));
		return best;
	}

	private static int mincut(int s, int t) {

		for (int i = 0; i < n; i++)
			System.arraycopy(cap[i], 0, res[i], 0, n);
		int x, flow = 0;
		do {
			Arrays.fill(used, false);
		} while ((x = findCapacity(s, t, INF)) != 0 && (flow += x) != 0);
		return flow;
	}

	static int findCapacity(int x, int t, int CurFlow) {
		if (x == t)
			return CurFlow;
		if (used[x])
			return 0;
		used[x] = true;
		for (int Flow, y = 0; y < n; y++) {
			if (res[x][y] > 0 && (Flow = findCapacity(y, t, Math.min(CurFlow, res[x][y]))) > 0) {
				res[x][y] -= Flow;
				res[y][x] += Flow;
				return Flow;
			}
		}
		return 0;
	}

}
