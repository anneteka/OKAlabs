
public class Tester {
	public static void main(String[] args) {
		SearchDictionary sd = new SearchDictionary("words.txt"); 
		System.out.println(sd.countWords());
		sd.addWord("Peenos");
		System.out.println(sd.countWords());
		System.out.println(sd.hasWord("Peenos"));
		sd.delWord("Peenos");
		System.out.println(sd.hasWord("Peenos"));
		System.out.println(sd.query("arr"));
		for(String s: sd.query("hell*")) {
			System.out.println(s);
		}
	}
}
