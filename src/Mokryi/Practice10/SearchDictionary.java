import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class SearchDictionary{

   private  Hashtable<String,Integer> dictionary;
   private static int wordsCount;
   private final ArrayList emptyArray;


    public SearchDictionary(){
        dictionary  = new Hashtable<>();
        wordsCount = 0;
        emptyArray = new ArrayList<>();
        emptyArray.add("Not Found!");
    }

    public void addWord(String word){
        int value = 1;
        if(hasWord(word))  value+=dictionary.get(word);
        dictionary.put(word, value);
        wordsCount++;
    }

    public String delWord(String word){
        if(hasWord(word)){
            dictionary.remove(word);
            wordsCount--;
            word+=" deleted!";
        }else{
            word="No such word!";
        }
        return word;
    }

    public boolean hasWord(String word){
        return  dictionary.containsKey(word);
    }


    public Iterable<String> query(String query){
        char[] queryCh = query.toCharArray();
        ArrayList<String> queryArray = new ArrayList<>();
        if(queryCh.length!=0&&queryCh[queryCh.length-1]=='*'){
        // Map.Entry<String,Integer> entry;
           for(String key : dictionary.keySet()) {
            char[] ch = key.toCharArray();
            int i = 0;
            boolean theSame = false;
            if (queryCh.length <= ch.length) {
                do {
                    theSame = (queryCh[i] == ch[i]) ? true : false;
                    i++;
                } while (i < (queryCh.length - 1) && theSame);
            }
            if(theSame)  queryArray.add(key);
            }
        }else{
            for(String key : dictionary.keySet())
                if(query.equals(key))
                    queryArray.add(key);
        }

        return !queryArray.isEmpty()? queryArray:emptyArray;
    }

    public int countWords(){
        return  wordsCount;
    }
}

