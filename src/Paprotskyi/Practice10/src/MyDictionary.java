import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDictionary {
    private
    Map<String,String> list;
    ArrayList<String> iter;
    public MyDictionary(){
        list = new HashMap<>();
        iter = new ArrayList<>();
    }

    public void addWord(String word,String value){
        list.put(word,value);
        iter.add(word);
    }

    public List<String> search(String wordStart){
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(iter.get(i).startsWith(wordStart)){
                res.add(iter.get(i)+" - "+list.get(iter.get(i)));
            }
        }
        return res;
    }

    public String delWord(String word){
        if(hasWord(word)){
            list.remove(word);
            return word;
        }else {
            return null;
        }
    }

    public int wordsCount(){
        return list.size();
    }

    public boolean hasWord(String word){
        return list.containsKey(word);
    }
}