package practice10;

public class Tester {

    private static SearchDictionary search;

    public static void main(String[] args) {
        search = new SearchDictionary();
        search.addWord("a");
        search.addWord("az");
        search.addWord("za");
        search.addWord("zz");
        search.addWord("zZbc");
        System.out.println(search.query("az*"));
        System.out.println(search.query("zz*"));
    }

}
