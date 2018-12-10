import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

public class SearchDictionary
{

     TreeSet<String> dictionary;
    private int countWord;

    public SearchDictionary()
    {
        dictionary = new TreeSet<String>();
    }

    public void addWord(String word)
    {
        countWord++;
        dictionary.add(word);
    }

    public String delWord(String word)
    {
        dictionary.remove(word);
        if(countWord>0)countWord--;
        return word;
    }

    public boolean hasWord(String word)
    {
        return dictionary.contains(word);
    }

    public Iterable<String> query(String query)
    {
        String from = dictionary.ceiling(query);



        SortedSet<String> temp = dictionary.tailSet(from);
        LinkedList<String> words = new LinkedList<>();

        for(String s: temp)
        {

            if(s.substring(0,query.length()-2).equals(query.substring(0,query.length()-2))) {
                words.add(s);
            }


        }

        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator()
            {
                return words.iterator();
            }
        };
    }

    public int countWords()
    {
        return countWord;
    }

}
