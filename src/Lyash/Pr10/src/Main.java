import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SearchDictionary searchDictionary = new SearchDictionary();

        searchDictionary.addWord("Kyiv","Київ");
        searchDictionary.addWord("Kiev","Київ");
        searchDictionary.addWord("Lviv","Львів");

        String s=sc.nextLine();

        s=s.replace("*","");

        List<String> rs=searchDictionary.jSearch(s);
        for (int i = 0; i <rs.size() ; i++) {
            System.out.println(rs.get(i));
        }
    }
}
