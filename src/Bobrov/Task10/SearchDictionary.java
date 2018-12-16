package Task10;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class SearchDictionary{

    private  Hashtable<String,Integer> dict;
    private static int wrdsNumber;
    private final ArrayList emptArray;

    public void addWord(String word){
        int value = 1;
        if(hasWord(word))  value+=dict.get(word);
        dict.put(word, value);
        wrdsNumber++;
    }

    public SearchDictionary(){
        dict  = new Hashtable<>();
        wrdsNumber = 0;
        emptArray = new ArrayList<>();
        emptArray.add("Not Found!");
    }

    public boolean hasWord(String word){
        return  dict.containsKey(word);
    }


    public String delWord(String word){
        if(hasWord(word)){
            dict.remove(word);
            wrdsNumber--;
            word+=" deleted!";
        }else{
            word="No such word!";
        }
        return word;
    }

    public int countWords(){
        return  wrdsNumber;
    }

    public Iterable<String> query(String query){
        char[] queryCh = query.toCharArray();
        ArrayList<String> queryArray = new ArrayList<>();
        if(queryCh.length!=0&&queryCh[queryCh.length-1]=='*'){
            // Map.Entry<String,Integer> entry;
            for(String key : dict.keySet()) {
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
            for(String key : dict.keySet())
                if(query.equals(key))
                    queryArray.add(key);
        }

        return !queryArray.isEmpty()? queryArray:emptArray;
    }
}


