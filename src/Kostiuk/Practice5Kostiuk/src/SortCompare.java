public class SortCompare {
	public static double time(String alg, Double[] a) {
		Stopwatch timer = new Stopwatch();
		if (alg.equals("Вставки"))
			Insertion.sort(a);
		if (alg.equals("Выбор"))
			Selection.sort(a);
		if (alg.equals("Шелла"))
			Shell.sort(a);
		if (alg.equals("Слияние"))
			Merge.sort(a);
		if (alg.equals("Быстрая"))
			Quick.sort(a);
		if (alg.equals("Пирамидальная"))
			Heap.sort(a);
		return timer.elapsedTime();
	}

	public static double timeRandomlnput(String alg, int N, int T) { // alg указывает алгоритм сортировки T случайных
																		// массивов длиной N.
		double total = 0.0;
		Double[] а = new Double[N];
		for (int t = 0; t < T; t++) { // Выполнение одного эксперимента (генерация и сортировка массива).
			for (int i = 0; i < N; i++) {а[i] = StdRandom.uniform();}
				
			total += time(alg, а);
		}
		return total;
	}

	public static void main(String[] args) {
		String algl = args[0];
		String alg2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T = Integer.parseInt(args[3]);
		double tl = timeRandomlnput(algl, N, T); // общее время для algl
		double t2 = timeRandomlnput(alg2, N, T); // общее время для alg2
		System.out.printf("Для %d случайных Doubles\n %s в", N, algl);
		StdOut.printf(" %.lf раз быстрее, чем %s\n", t2 / tl, alg2);
	}
	// 
}