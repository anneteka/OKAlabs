import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Tester {
	private final static String testFile="\\input.txt";
	static Scanner br;
			//BufferedReader br= new BufferedReader(new FileReader(new File(testFile)));
			BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
			// PrintWriter out = new PrintWriter("output.txt");
			 PrintWriter out = new PrintWriter(System.out, true);
		
	public static void main(String[] args) throws IOException {
		br= new Scanner(new File(testFile));
		Tester s= new Tester();
		s.inputNumber();
	}

	private String ask() {
		String word="";
		out.println("Введіть слово: ");
		try {
			String [] a= in.readLine().split(" ");
			if(a.length>1) ask();
			else word=a[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return word;
	}

	

	private void askAndoutput() {
		while(true) {
			String toFind=ask();
			printWords(toFind);
		}
	}
	private void printWords(String toFind) {
		Iterable<String> it=sd.query(toFind);
		if(it==null || !it.iterator().hasNext()) out.println("Таких слів не знайдено");
		else {
		out.println("Знайдені слова: ");
		for(String str:it) {
			out.println(str);
		}
		}
		out.println("");
		
	}
	SearchDictionaryTree sd;
	private void inputNumber() {
		sd= new SearchDictionaryTree();
		while(br.hasNext()) {
			sd.addWord(br.next());
		}
		askAndoutput();
		
	}

}
