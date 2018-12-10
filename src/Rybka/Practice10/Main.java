import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SearchDictionary dictionary = new SearchDictionary();
		try {
			dictionary.readFromFile("input.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*dictionary.addWord("apple");
		String s =dictionary.delWord("apple");
		System.out.println(s);
		System.out.println(dictionary.hasWord(s));
		System.out.println(dictionary.hasWord("Lorem"));
		System.out.println(dictionary.getWord(s));
		System.out.println(dictionary.getWord("Lorem"));
		*/
		System.out.println("Write a word or first letter with'*' \n");
		Scanner scr = new Scanner(System.in);
		String word = scr.nextLine();
		if(word.contains("*"))
			System.out.println(dictionary.query(word.substring(0,word.length()-1)));
		else
			System.out.println(dictionary.getWord(word));

	}

}
