import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;



public class SearchDictionary {
	HashMap<String, HashSet<String>> allWorld;
	int count =0;
	public SearchDictionary(){
		allWorld= new HashMap<String, HashSet<String>>();
	}

	public void addWord(String word){
		if(!hasWord(word)) {
		if(count==0) addKey("*", word);
		else allWorld.get("*").add(word);
		String changingStr="";
		for(int i=0; i<word.length(); i++) {
			changingStr+=word.charAt(i);
			if(allWorld.containsKey(changingStr)) allWorld.get(changingStr).add(word);
			else addKey(changingStr, word);
		}
		count++;
		}
	}

	private void addKey(String changingStr, String word) {
		HashSet<String> l= new HashSet<String>();
		l.add(word);
		allWorld.put(changingStr, l);
	}
	
	public String delWord(String word){
		if(hasWord(word)) {
		if(allWorld.get("*").size()==1) allWorld.remove("*");
		else allWorld.get("*").remove(word);
		String changingStr="";
		for(int i=0; i<word.length(); i++) {
			changingStr+=word.charAt(i);
			if(allWorld.get(changingStr).size()==1) allWorld.remove(changingStr);
			else allWorld.get(changingStr).remove(word);
		}
		count--;
		}
		return word;
	}

	public boolean hasWord(String word){
		return allWorld.containsKey(word);
	}


		/*public Iterable<String> query(String query){
		if(query.charAt(query.length()-1)=='*') {
			String newStr="";
			for(int i=0; i<query.length()-1; i++) {
				newStr+=query.charAt(i);
			}
			query=newStr;
		}
		Iterable<String> it= new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				Iterator<String> i= new Iterator<String>() {
					int n=allWorld.get(query).size();
					int cur=0;
					 String[] newItem=new String[n];
					@Override
					public boolean hasNext() {
						if(cur==0) {
							int i=0;
							for(String s: allWorld.get(query)) {
								newItem[i]=s;
								i++;
							}
							Arrays.sort(newItem);
						}
						return cur<n;
					}

					@Override
					public String next() {
						String toReturn=newItem[cur];
						cur++;
					    return toReturn;
					}
				};
				return i;
			}
		};
		return it;
	}*/

	public int countWords(){
		return count;
	}
}
