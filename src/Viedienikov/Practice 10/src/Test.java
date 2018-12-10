import java.io.IOException;


public class Test {
    public static void main(String[] args) throws IOException {
        dictionary searchDictionary = new dictionary();
        searchDictionary.addWord("yellow");
        searchDictionary.addWord("red");
        searchDictionary.addWord("black");
        searchDictionary.addWord("blue");
        searchDictionary.addWord("purple");
        searchDictionary.addWord("brown");
        searchDictionary.addWord("grey");
        searchDictionary.addWord("rose");
        searchDictionary.addWord("white");
        searchDictionary.addWord("purple-black");
        searchDictionary.addWord("yellow-red");
        System.out.println(searchDictionary.query("r*"));
        System.out.println(searchDictionary.delWord("rose"));
        System.out.println(searchDictionary.query("r*"));
        System.out.println(searchDictionary.countWords());
        }}
