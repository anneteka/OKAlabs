/**
 * алгоритм для зв'язування деяких об'єктів
 *
 */
public class QuickFindUF {
	private int[] id;

	/*
	 * Конструктор, що ініціює масив, а потім присвоює кожному об'єкту окрему групу
	 */
	public QuickFindUF(int n) {
		id = new int[n];
		for (int i = 0; i < n; i++)
			id[i] = i;
	}

	/*
	 * метод, що перевіряє чи знаходяться p та q в одному компоненті
	 */
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	/*
	 * метод, що робить два об'єкти p та q пов'язаними
	 */
	public int find(int p) {
		return id[p];
	}

	public void union(int p, int q) {
		int pid = find(p);
		int qid = find(q);
		if (pid == qid)
			return;
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid)
				id[i] = qid;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i : id) {
			str.append(i);
			str.append(" ");
		}
		return str.toString();
	}
}
