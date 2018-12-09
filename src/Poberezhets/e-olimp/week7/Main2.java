package pr7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
		String s=r.readLine();	
		if(s==null)
			break;
		String numbers[] = s.split(" ");
		
		int second[] = new int[numbers.length];
		ArrayList<Integer> arr=new ArrayList<>(); 
		for (int i = 0; i < second.length; i++)
			arr.add(Integer.parseInt(numbers[i]));
		boolean isOk=true;
		while(isOk) {
			int k=arr.get(0);
			if(k>arr.size()-1) {
				isOk=false;
				break;
			}
			for(int i=1; i<=k;i++) {
				arr.set(0,arr.get(0)-1);
				arr.set(i,arr.get(i)-1);
			}
			//System.out.println("before"+arr.toString());
			sort(arr);
			//System.out.println("after"+arr.toString());

			while(arr.size()!=0&&arr.get(arr.size()-1)==0)
				arr.remove((arr.size()-1));
			//System.out.println("delete 0"+arr.toString());
			if(arr.isEmpty()) {
				isOk=true;
				break;
			}
			if(arr.size()==1) {
				isOk=false;
				break;
			}
		}
		if(isOk==true) 
			System.out.println("ok");
			
		else System.out.println("fail");
		System.out.println();
		}
		
	}
	
	private static ArrayList<Integer> sort(ArrayList<Integer> array) {
	    int n = array.size();
	    for (int i = 0; i < n; i++) {
	      for (int j = i; j > 0; j--)
	        if (array.get(j) > array.get(j-1)) {
	          int temp = array.get(j);
	          array.set(j, array.get(j-1));
	          array.set(j-1, temp);
	        } else
	          break;
	    }
	    return array;
	  }
}

