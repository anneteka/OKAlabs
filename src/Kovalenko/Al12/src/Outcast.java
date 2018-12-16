public class Outcast{
    private WordNet wordnet;
// constructor takes a WordNet object     
public Outcast(WordNet _wordnet)      // given an array of WordNet nouns, return an outcast    
{
  wordnet = _wordnet;
}

public String outcast(String[] nouns)      // for unit testing of this class (such as the one below)  
{
    //di = dist(Ai, A1) + dist(Ai, A2) + ... + dist(Ai, An)
    int max =0;
    String outcast =null;
    for(String wordA : nouns){
        int newMax =0; 
        for(String wordB: nouns){
        //get distance of this one to... which one?
            if(wordA!=wordB){
                newMax += wordnet.distance(wordA,wordB);
            }
            if(newMax>max);
            {
                max = newMax;
                outcast = wordA;
            }
        }   
    }
    return outcast;
}

public static void main(String[] args) {         
    WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");          
    Outcast outcast = new Outcast(wordnet);          
    for (int t = 2; t < args.length; t++) {              
        String[] nouns = In.readStrings(args[t]);              
        StdOut.println(args[t] + ": " + outcast.outcast(nouns));          
    } 
  }
}