package Practice9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Contacts {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Contacts obj = new Contacts();
		obj.doTask();
	}

	private void doTask() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(br.ready()) {
				int n = Integer.parseInt(br.readLine());
				String[] numbers = new String[n];
				boolean is = true;
				for(int j=0; j<n; j++) 
					numbers[j] = br.readLine();
				Arrays.sort(numbers);
				for(int k = 1; k<n; k++) {
					if(is==false) break;
						if(numbers[k].startsWith(numbers[k-1])) {
							System.out.println("NO");
							is = false;
							break;
						}
				}
				if(is) System.out.println("YES");
		}
	}

}
