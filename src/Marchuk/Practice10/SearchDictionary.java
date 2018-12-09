package Practice10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SearchDictionary {

	private LinkedList<String>[] words;
	
	public SearchDictionary() {
		words = new LinkedList[37];
	}

	public void addWord(String word) {
		if(word.length()==0)
			return;
		
		int index = getIndex(word);
		if(words[index]==null)
			words[index] = new LinkedList<String>();
		words[index].add(word);
	}

	public boolean delWord(String word) {
		if(word.length()==0)
			return false;
		
		int index = getIndex(word);
		if(words[index]==null)
			return false;
		
		return words[index].remove(word);
	}

//	public boolean hasWord(String word) {
//		
//	}

	public ArrayList<String> query(String query) {
		ArrayList<String> list = new ArrayList<String>();
		
		
		if(query.length()>1 && query.charAt(query.length()-1)=='*') {
			query = query.substring(0, query.length()-1);
			int index = getIndex(query);
			if(words[index] != null)
				for(String st: words[index])
					if(st.startsWith(query)) {
						list.add(st);
					}
			return list;
		}
		
		if(query.length()>0 && query.charAt(query.length()-1) != '*') {
			int index = getIndex(query);
			if(words[index] != null)
				for(String st: words[index])
					if(st.equals(query)) {
						list.add(st);
					}
			return list;
		}
		
		for (LinkedList<String> ll : words)
			if(ll != null)
				for(String st: ll) {
					list.add(st);
				}
		
		return list;
	}

	public int countWords() {
		int counter = 0;
		
		for(LinkedList<String> li : words) {
			if(li!=null)
				counter+= li.size();
		}
		
		return counter;
	}
	
	private int getIndex(String st) {
		st = st.toLowerCase();
		char ch = st.charAt(0);
		
		if(ch>=48 && ch<=57) {
			return ch-48;
		}
		if(ch>=97 && ch<=122) {
			return ch-87;
		}
		
		return 36;
	}
	public static void main(String[] args) throws IOException {
		File fl = new File("D:\\OKA\\Practice\\Practice10\\testdict.txt");
		BufferedReader br = new BufferedReader(new FileReader(fl));
		String[] tmp = br.readLine().split(" ");
		SearchDictionary sd = new SearchDictionary();
		for(int i=0; i<tmp.length; i++) {
			sd.addWord(tmp[i].toLowerCase());
		}
		ArrayList<String> resWords = sd.query("l*");
		for(int j=0; j<resWords.size(); j++) {
			System.out.println(resWords.get(j));
		}
	}
}
