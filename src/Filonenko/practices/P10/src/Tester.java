
public class Tester {
    public static void main(String[] args) {
        SearchDictionary dictionary = new SearchDictionary();
        dictionary.addWord("first");
        dictionary.addWord("second");
        dictionary.addWord("third");
        System.out.println(dictionary.query("x*"));
    }
}
