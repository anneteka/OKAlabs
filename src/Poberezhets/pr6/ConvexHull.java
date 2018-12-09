package ConvexHull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import lib.StdDraw;
/**(ДОНЕ)
 * Зчитуэ з файлу, сортує, малює опуклу оболонку
 * @author Богдана
 *
 */
//ConvexHull, що зчитує з файла точки, малює їх, знаходить опуклу оболонку і малює її.
public class ConvexHull {
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
		ConvexHull hull = new ConvexHull();
		hull.readPoint("grid6x6.txt");

	}
/**
 * зчитує файл
 * записує всі точки в масив
 * сортує від найнижчої до найвищої
 * сортує відносно кута найнижчої
 * @param fileName
 * @return
 */
	private Point2D[] readPoint(String fileName) {
		Point2D[] points = null;
		try {
			Scanner in = new Scanner(new File(fileName));
			int n = in.nextInt();
			points = new Point2D[n];
			for (int i = 0; i < n; i++)
				points[i] = new Point2D(in.nextInt(), in.nextInt());

		} catch (IOException e) {
			System.out.println("No file found");
		}
		MergeSort.sort(points);
		for (Point2D p : points)
			System.out.println(p);

		MergeSort.sort(points, points[0].SLOPE_ORDER);

		System.out.println("------------------------");
		for (Point2D p : points)
			System.out.println(p);
		makeHulls(points);
		return points;
	}
	/**
	 * Якщо трійка p1p2p3 утворює правий поворот, то видалити вершину p2 та
	 * перевірити трійку p0p1p3. 2. Якщо трійка p1p2p3 утворює лівий поворот, то
	 * продовжувати перегляд, перейшовши до трійки p2p3p4. 
	 * Якщо результат 0, точки
	 * колінеарні; якщо позитивний, точки утворюють поворот ліворуч, інакше поворот
	 * праворуч.
	 * 
	 * @param points
	 * @return
	 */
	/**
	 * переписує в Арай ліст
	 * видаляє всі непотрібні точки з ліста
	 * малює опуклу оболонку
	 * @param points
	 */
	private void makeHulls(Point2D[] points) {
		ArrayList<Point2D> drawPoint = new ArrayList<Point2D>();
		for(int i=0; i<points.length;i++) {
			drawPoint.add(points[i]);
		}
		for(Point2D p: drawPoint)
			p.draw();
		
		int i=0; 	
		while(i+2!=drawPoint.size()) {
			Point2D p1=drawPoint.get(i);
			Point2D p2=drawPoint.get(i+1);
			Point2D p3=drawPoint.get(i+2);
			if(Point2D.ccw(p1, p2, p3)>0){
				i++;
			}if(Point2D.ccw(p1, p2, p3)<=0) {
				drawPoint.remove(p2);
				if(i!=0)
					i--;
			}		
		}
		
		for(int j=0;j<drawPoint.size()-1;j++) {
			drawPoint.get(j).drawTo(drawPoint.get(j+1));
		}
		drawPoint.get(drawPoint.size()-1).drawTo(drawPoint.get(0));
	}

}
