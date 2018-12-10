import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SearchDictionary sd = new SearchDictionary();

        sd.addWord("Kyiv","Київ");
        sd.addWord("Kiev","Київ");
        sd.addWord("Dn","Дніпро");

        String s=sc.nextLine();
        if(s.endsWith("*"))
            s = s.replace("*","");

        List<String> results=sd.jSearch(s);
        for (int i = 0; i <results.size() ; i++) {
            System.out.println(results.get(i));
        }
    }
}