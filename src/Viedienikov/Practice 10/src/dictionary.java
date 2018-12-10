import java.util.ArrayList;
import java.util.Hashtable;

public class dictionary {
    private  Hashtable<String,Integer> table;
    private int count;
    public dictionary(){
        table=new Hashtable<String,Integer>();
    }
    public void addWord(String word){
        int num=1+word.hashCode();
        table.put(word,num);
        count++;
    }

    public String delWord(String word){
        if(hasWord(word)){
            table.remove(word);
            count--;
            word+=" deleted!";
        }else{
            word="Can't find this word!";
        }
        return word;
    }

    public boolean hasWord(String word){
        return table.containsKey(word);
    }

    public Iterable<String> query(String query){
        ArrayList<String> res = new ArrayList<>();
        char[]charArr=query.toCharArray();
        int size=charArr.length;
        if(charArr[size-1]=='*'){
            for(String key:table.keySet()){
                char[]k=key.toCharArray();
                int i=0;
                while (i<size) {
                    if (charArr[i] == k[i]) ;
                    else break;
                    i++;
                }
                if(i==size-1)res.add(key);
            }
        }else {
            for(String key : table.keySet())
            if(query.equals(key))
                res.add(key);
        }
        if(res.isEmpty())res.add("Not Found");
        return res;
    }

    public int countWords(){ return count;}

}
