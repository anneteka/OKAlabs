import java.io.*;
import java.util.Scanner;

public class Main
{
    private SearchDictionary sd = new SearchDictionary();
    private File file = new File("src/dictionary.txt");



    public static void main(String args[]) throws IOException
    {
        Main m = new Main();
        m.fillDictionary();
        m.test();
    }
    private void fillDictionary() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null)
        {
            sd.addWord(st);
        }
    }

    private void test()
    {
//       for(String s: sd.dictionary)System.out.println(s);
            for(String s: sd.query("rj"))
            System.out.println(s);
            sd.delWord("rjaka");

    }
}
