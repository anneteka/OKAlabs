
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tester {

   private  static Iterable<String> words;

    public static void main(String[] args) throws IOException {

        SearchDictionary searchDictionary = new SearchDictionary();
        BufferedReader br =new BufferedReader(new FileReader("word_rus.txt"));


        String line;
        while((line = br.readLine()) != null){
            searchDictionary.addWord(line);
        }



        br =new BufferedReader(new InputStreamReader(System.in));
        String inStr;
        while((inStr  = br.readLine()) != null) {
           if(!inStr.equals("")) {
               String[] parts = inStr.split(" ");
               if (parts[0].equals("del") && parts.length == 2) {
                   String deletedW=  searchDictionary.delWord(parts[1]);
                   System.out.println( deletedW );

               } else if (parts[0].equals("end")) {
                   System.out.println("Program ended...");
                   return;

               } else {
                   words = searchDictionary.query(inStr);
                   for (String str : words) {
                       System.out.println(str);
                   }
               }
               System.out.println();
           }
        }
        System.out.println(searchDictionary.countWords());
        br.close();



    }
}
