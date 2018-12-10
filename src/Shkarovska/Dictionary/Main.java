import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		SearchDictionary dic;
		try {
			BufferedReader br = new BufferedReader(new FileReader("file.txt"));
			String str = br.readLine();
			dic = new SearchDictionary();
			
			while(str!=null) {
				String x[] = str.split(" ");
				for(int i=0; i<x.length; i++)
					dic.addWord(x[i]);
				str = br.readLine();
			}
			System.out.print("Введіть початок слова: ");
			String find = getString();
			ArrayList<String> search = dic.getArray(find.charAt(0));
			if(search == null) return;
			search = findWords(search, find);
			if(search == null)
				System.out.println("Таких слів не знайдено");
			else {
				for(int i=0; i<search.size(); i++) {
					System.out.println((i+1)+") "+search.get(i));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
	}
	private static ArrayList<String> findWords(ArrayList<String> search, String find) {
		ArrayList<String> res = new ArrayList<>();
		for(int i=0; i<search.size(); i++) {
			String now = search.get(i);
			if(now.length() >= find.length()) {
				if(now.startsWith(find))
					res.add(now);
			}
		}
		return res;
	}
	private static String getString() {
		Scanner sc = new Scanner(System.in);
		String rs = sc.nextLine();
		String res = "";char ch1 = ' '; char ch2 = '*' ;
		for(int i=0; i< rs.length(); i++) {
			if(rs.charAt(i)!=ch1 && rs.charAt(i)!=ch2) {
				res+=rs.charAt(i);
			}
		}
		return res;
	}

}
