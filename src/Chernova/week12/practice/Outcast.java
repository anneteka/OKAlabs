package week12.practice;


import java.io.*;
import java.util.ArrayList;

public class Outcast{
    private WordNet wordnet;

    public Outcast(WordNet wordnet)
    {
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns)
    {
        int max =0;
        String outcast =null;
        for(String wordA : nouns){
            int newMax =0;
            for(String wordB: nouns){
                if(!wordA.equals(wordB)){
                    newMax += wordnet.distance(wordA,wordB);
                }
                if(newMax>max)
                {
                    max = newMax;
                    outcast = wordA;
                }
            }
        }
        return outcast;
    }


    public static void main(String[] args) throws IOException {
        WordNet wordnet = new WordNet("src/week12/practice/synsets.txt", "src/week12/practice/hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        BufferedReader bf = new BufferedReader(new FileReader("src/week12/practice/outcast.txt"));
        ArrayList<String> al = new ArrayList<String>();
        String s = " ";

        while(bf.ready()){
            if (s.equals("")) break;
            s = bf.readLine();
            al.add(s);
        }

        String[] nouns = new String[al.size()];
        nouns = al.toArray(nouns);
        System.out.println(outcast.outcast(nouns));
    }
}
