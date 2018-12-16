import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyDictionary md = new MyDictionary();

        md.addWord("Kyiv","Київ");
        md.addWord("Kiev","Київ");
        md.addWord("Rivne","Рівне");
        md.addWord("Rovno","Рівне");

        String s=sc.nextLine();
        if(s.endsWith("*"))
            s = s.replace("*","");

        List<String> results=md.jSearch(s);
        for (int i = 0; i <results.size() ; i++) {
            System.out.println(results.get(i));
        }
    }
}