import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Maria Skyrta. E-olymp turn oriaented graph.
 *
 */
public class MyGraph {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s = br.readLine();
			int n = Integer.parseInt(s);
			String[] arr = new String[n + 1];
			for (int i = 1; i < arr.length; i++) {
				arr[i] = "";
			}
			// s = br.readLine();
			for (int i = 1; i < arr.length; i++) {
				for (String str : br.readLine().split(" ")) {
					if (str.equals("")) {
						break;
					}
					arr[Integer.parseInt(str)] += i + " ";
				}
			}
			System.out.println(n);
			for (int i = 1; i < arr.length; i++) {
				System.out.println(arr[i]);
			}
		}
	}

}
