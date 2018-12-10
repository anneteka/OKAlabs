package dictionary;

import java.util.ArrayList;
import java.util.Map;

public class Tester {
	
	
	public static void main(String args[]) 
	{
		
		SearchDictionary dictionary = new SearchDictionary();
		
		dictionary.addWord("first14");
		
		dictionary.addWord("bab");
		
		dictionary.addWord("bad");
		
		dictionary.addWord("baca");
		
		dictionary.addWord("afi");
		
		dictionary.addWord("first1");
		
		dictionary.addWord("faradenza");
		
		dictionary.addWord("first12");
		
		dictionary.addWord("first123");

		dictionary.addWord("first1234");
		
		dictionary.addWord("fi");
		
		dictionary.addWord("rs");
		
		dictionary.addWord("fia");
		
		dictionary.addWord("fi");
		
		dictionary.addWord("fido");
		
		dictionary.addWord("fidko");
		
		dictionary.addWord("a");
		
		dictionary.delWord("a");
		
		dictionary.delWord("f");
		
		dictionary.addWord("ba");
		
		dictionary.addWord("bor");
		
		//System.out.println("Quantity of words:" + dictionary.countWords());
	
		ArrayList<String> res = (ArrayList<String>) dictionary.query("first12*");
		
		System.out.println(res);
		
		
	}
}
