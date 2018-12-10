package dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchDictionary {
	
	public Map<String,SearchDictionary> children;
	
	int words;
	
	public SearchDictionary(){
		children = new HashMap<String, SearchDictionary>();
		words = 0;
	}

	public void addWord(String word){
		if(word == null) return;
		if(word.isEmpty()) return;
		if(children.isEmpty()){
			children.put(word, new SearchDictionary());
			words++;
			return;
		}else{
			
			boolean added = false;
			
			for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
				if(entry.getKey().equals(word)) return;
				
				//if the new word is completely inaide existing one, divide ALL the existing one
				if(entry.getKey().contains(word) && word.charAt(0) == entry.getKey().charAt(0))
				{
					ArrayList<String> keys = new ArrayList<>();
					ArrayList<SearchDictionary> values = new ArrayList<>();
					
					for(Map.Entry<String, SearchDictionary> entry2 : children.entrySet()) {
						if(entry2.getKey().contains(word) && word.charAt(0) == entry2.getKey().charAt(0))
						{
							values.add(entry2.getValue());
							keys.add(entry2.getKey());
						}
					}
					for(int i = 0;i < keys.size();i++)
					{
						children.remove(keys.get(i));
						keys.set(i, keys.get(i).replace(word,""));
					}
					
					children.put(word, new SearchDictionary());
					
					for(int i = 0;i < keys.size();i++)
					{
						children.get(word).children.put(keys.get(i), values.get(i));
					}
					words++;
					added = true;
					return;
				}
				//if the new word is bigger than existing ones,but i completely contains one
				if(word.contains(entry.getKey()) && word.charAt(0) == entry.getKey().charAt(0))
				{
					words++;
					entry.getValue().addWord(word.replace(entry.getKey(), ""));
					added = true;
					return;
				}
			}
			
			if(!added)
			{
				children.put(word, new SearchDictionary());
				words++;
			}
		}
	}

	public String delWord(String word){
		if(word == null) return word;
		if(word.isEmpty()) return word;
		
		//if exists then delete
		if(hasWord(word))
		{
			delete(word);
			words--;
		}
		
		return word;
	}
	
	private void delete(String word)
	{
		
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
				
			if(word.contains(entry.getKey()) && word.charAt(0) == entry.getKey().charAt(0))
			{
				if(word.replace(entry.getKey(), "").isEmpty())
				{
					ArrayList<String> keys = new ArrayList<>();
					ArrayList<SearchDictionary> values = new ArrayList<>();
					
					for(Map.Entry<String, SearchDictionary> entry2 : entry.getValue().children.entrySet()) {
						values.add(entry2.getValue());
						keys.add(entry2.getKey());
					}
					
					entry.getValue().children.clear();
					for(int i = 0;i < keys.size();i++)
					{
						entry.getValue().children.put(entry.getKey() + keys.get(i), values.get(i));
					}
					
					for(Map.Entry<String, SearchDictionary> entry2 : entry.getValue().children.entrySet()) {
						children.put(entry2.getKey(), entry2.getValue());
					}
		
					children.remove(entry.getKey());
					return;
					
				}
				else
				{
					entry.getValue().delete(word.replace(entry.getKey(), ""));
				}
			}
		}
	}
	
	
	public boolean hasWord(String word){
		if(word == null) return false;
		if(word.isEmpty()) return false;
		word = word.trim();
		if(word.contains("*"))
		{
			word = word.substring(0, word.indexOf("*") + 1);
			return hasWordRec(word);
		}
		else
		{
			return hasWordStrict(word);
		}	
	}
	
	private boolean hasWordRec(String word){
		if(word == null) return false;
		if(word.isEmpty()) return true;
		//if(word.trim().equals("*") && !children.isEmpty()) return true;
		
		//when dko and d*
		
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
			
			if(word.contains(entry.getKey()) && word.charAt(0) == entry.getKey().charAt(0))
			{
					
				return entry.getValue().hasWordRec(word.replace(entry.getKey(), ""));
			}
		}
		
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
			word = word.replace("*", "");
			if(entry.getKey().contains(word))
			{
				return true;
			}
		}
		
		return false;
	}

	private boolean hasWordStrict(String word){
		if(word == null) return false;
		if(word.isEmpty()) return true;
		
		
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
				
			if(word.contains(entry.getKey()) && word.charAt(0) == entry.getKey().charAt(0))
			{
					
				return entry.getValue().hasWordStrict(word.replace(entry.getKey(), ""));
			}
		}
		
		return false;
	}
	
	public Iterable<String> query(String word){
		ArrayList<String> results = new ArrayList<>();
		if(!hasWord(word))
		{
			return results;
		}
		else
		{
			if(word.contains("*"))
			{
				word = word.trim();
				word = word.substring(0, word.indexOf("*") + 1);
				String prefix = "";
				results = (ArrayList<String>) addResults(word, results,prefix);
			}
			else
			{
				results.add(word);
			}	
		}
		return results;
	}
	
	private ArrayList<String> addResults(String word,ArrayList<String> res,String prev)
	{
		
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
			if(word.contains(entry.getKey()) && word.charAt(0) == entry.getKey().charAt(0))
			{
				word = word.replace(entry.getKey(), "" );
				entry.getValue().addResults(word,res,prev + entry.getKey());
				word = "";
			}
		}
		
		
		if(word.equals("*"))
		{
			res.add(prev);
			endResOnlyStar(res,prev);
			return res;
		}
		else if(!word.isEmpty())
		{
			for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
				
				if(entry.getKey().contains(word.replace("*", "")))
				{
					res.add(prev+ entry.getKey());
					entry.getValue().endRes1(res,prev + entry.getKey());
				}
			}
			
			return res;
		}
		
		return res;

	}
	
													
	private ArrayList<String> endRes1(ArrayList<String> res,String prev)
	{
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
			
			res.add(prev + entry.getKey());
			entry.getValue().endRes1(res,prev + entry.getKey());
		}
		return res;
	}
	
	private ArrayList<String> endResOnlyStar(ArrayList<String> res,String prev)
	{
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
			
			res.add(prev + entry.getKey());
			entry.getValue().endResOnlyStar(res,prev + entry.getKey());
		}
		return res;
	}
	
	public void printAllParts(){
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
			System.out.println(entry.getKey());
			entry.getValue().printAllParts();
		}
	}
	
	public void printAll(){
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
			System.out.println(entry.getKey());
			entry.getValue().printAll(entry.getKey());
		}
	}
	
	public void printAll(String parentWord){
		for(Map.Entry<String, SearchDictionary> entry : children.entrySet()) {
			System.out.println(parentWord + entry.getKey());
			entry.getValue().printAll(parentWord + entry.getKey());
		}
	}

	public int countWords(){
		return words;
	}
}
