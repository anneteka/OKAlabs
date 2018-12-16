package project;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class Outcast {
	 private WordNet wordnet;
	// конструктор приймає об’єкт WordNet 
	public Outcast(WordNet wordnet) {
		 this.wordnet = wordnet;
	}
	// маючи масив WordNet іменників, повернути «ізгоя»
	public String outcast(String[] nouns) {
		int max = 0;
        String temp, out = null;
        for (int dis = 0, i = 0; i < nouns.length; i++, dis = 0) {
            temp = nouns[i];
            for (String noun : nouns) {
                dis += wordnet.distance(temp, noun);
            }            

            if (dis > max) {
                max = dis;
                out = temp;
            }   
        }

        return out;
        }
	public static void main(String[] args) {
		WordNet wordnet = new WordNet("src/data/synsets.txt", "src/data/hypernyms.txt");
		Outcast outcast = new Outcast(wordnet);
		for (int t = 0; t < args.length; t++) { 
		String[] nouns = In.readStrings(args[t]); 
		StdOut.println(args[t] + ": " + outcast.outcast(nouns)); 
		} 

	}

}
