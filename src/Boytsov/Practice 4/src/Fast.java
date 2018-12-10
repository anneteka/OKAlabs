import java.util.Arrays;

/*	-Думайте про p як про основну.
	-Для кожної іншої точки q визначимо нахил до p.
	-Відсортуємо точки за нахилом, що вони роблять з p
	-Перевіряємо чи існує 3 (або більше) сусідніх точок в впорядкованій множині, що мають однаковий нахил до p.
	-Якщо так, ці точки разом з p колінеарні.
	-Застосування цього методу до кожної з N точок, у свою чергу дає ефективний алгоритм.*/

public class Fast {

	public static void analyze(Point[] points) {
		Point[] aux = new Point[points.length];

		aux = points.clone();
		for (int h = 0; h < points.length; h++) {
			Point thePoint = points[h];
			Arrays.sort(aux, points[h].SLOPE_ORDER);

			double[] slope_aux = new double[points.length - 1]; // for testing
			for (int i = 0; i < aux.length - 1; i++) {
				slope_aux[i] = aux[i].slopeTo(thePoint);
			}

			int count = 1;
			for (int i = 0; i < aux.length - 1; i++) {
				double slope1 = aux[i].slopeTo(thePoint);
				while (i < aux.length - 1 && slope1 == aux[i + 1].slopeTo(thePoint)) {
					count++;
					i++;
				}
				if (count>=3) {
					thePoint.drawTo(aux[i]);
					StdOut.print(thePoint + " -> ");
					for (int j = i-count+1; j < i; j++) {
						StdOut.print(aux[j] + " -> ");
					}
					StdOut.println(aux[i]);
					count = 1;
				}else {
					count = 1;
				}
			}

		}
	}
}