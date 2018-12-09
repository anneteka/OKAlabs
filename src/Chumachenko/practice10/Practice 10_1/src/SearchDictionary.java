import java.io.*;
import java.util.*;


public class SearchDictionary {
	
LinkedHashSet<String> dictionary;

int wordCount;

public SearchDictionary(){
	dictionary = new LinkedHashSet<>();
    wordCount = 0;
}
public SearchDictionary(File file){
	dictionary = new LinkedHashSet<>();
    wordCount = 0;
    createDictionary(file);

}

private void createDictionary(File file) {
	try {
		Scanner sc = new Scanner (file);
		while(sc.hasNext()) 
			addWord(sc.next());
		
		sc.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
}

public void addWord(String word){
	if(!hasWord(word)) wordCount++;
	dictionary.add(word);
	
}


public String delWord(String word){
	dictionary.remove(word);
	wordCount --;
	return word;
}

public boolean hasWord(String word){
	return dictionary.contains(word);
}


public Iterable<String> query(String query){
	ArrayList<String> a = new ArrayList<String>();
	
	if(!query.contains("*")) {
		
		if(hasWord(query)) a.add(query);
		return a;
	}
    int length = (query.length()-1);
	
    for(String word: dictionary) {
		if(length>word.length()) continue;
		boolean contains = true;
		for(int i = 0; i<length; i++) {
			if(query.charAt(i) != word.charAt(i)) {
				contains = false;
				break;
			}
		}
		if(contains) a.add(word);
	}
	return a;
}



public int countWords(){
	return wordCount;
} 
}
