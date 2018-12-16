import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchDictionary {
    private
    Map<String,String> list;
    ArrayList<String> iter;
    public SearchDictionary(){
        list = new HashMap<>();
        iter = new ArrayList<>();
    }

    public void addWord(String word,String value){
        list.put(word,value);
        iter.add(word);
    }

    public List<String> jSearch(String wordStart){
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

    public boolean hasWord(String word){
        return list.containsKey(word);
    }

    public int countWords(){
        return list.size();
    }
}