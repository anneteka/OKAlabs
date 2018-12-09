import java.io.File;
import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		SearchDictionary d = new SearchDictionary(new File("file.txt"));
		System.out.println(d.wordCount);
		d.addWord("ZvvnvGkkk");
		d.addWord("ZvnvGkk");
		d.addWord("Zvttkk");
		d.addWord("AdrvGkk");
		d.addWord("AAAAAAA");
		d.addWord("AAAAAAA");
		System.out.println(d.wordCount);
		
		ArrayList<String> word2 = (ArrayList<String>) d.query("*");
    	ArrayList<String> word4 = (ArrayList<String>) d.query("Zv*");

		System.out.println(word2.toString());
	    System.out.println(word4.toString());
	}
}
