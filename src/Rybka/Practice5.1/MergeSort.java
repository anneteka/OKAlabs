import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class MergeSort {
	
	private static void sort(Robot[] robots) {
        Number currentNum;
        Arrays.sort(robots);
        PrintWriter pw = new PrintWriter(System.out);
        for (Robot id : robots) pw.println(id.firstID + " " + id.secondID);
        pw.close();
    }

	static class Robot implements Comparable<Robot> {
		int firstID, secondID, number;

		Robot(int i, int j, int k) {
			this.firstID = i;
			this.secondID = j;
			this.number = k;
		}

		@Override
		public int compareTo(Robot other) {
			int c = this.firstID - other.firstID;
			if (c == 0)
				return this.number - other.number;
			else
				return c;
		}
	}

	public static void main(String[] args) throws IOException {
		//new FileReader("G:/NaUKMA/OKA/Input(MergeSort).txt")
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(buf.readLine());
		Robot arr[] = new Robot[N];
		String strArray[] = new String[2];
		for (int i = 0; i < N; i++) {
			strArray = buf.readLine().split(" ");
			arr[i] = new Robot(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]), i);
		}
		sort(arr);
		buf.close();
	}
}