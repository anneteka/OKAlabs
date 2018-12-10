
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;


public class SearchDictionary {
	SET<String> dict;

	public SearchDictionary() {
		dict = new SET<String>();
	}

	public void addWord(String word) {
		dict.add(word);
	}

	public void delWord(String word) {
		dict.delete(word);
	}

	public boolean hasWord(String word) {
		return dict.contains(word);
	}

	public Iterable<String> query(String query) {
		ArrayList<String> jokerArr = new ArrayList<>();
		Iterator<String> iterator = dict.iterator();
		boolean flag;
		while (iterator.hasNext()) {
			flag = false;
			String next = iterator.next();
			if (query.charAt(0) == '*') {
				jokerArr.add(next);
			} else {
				if (next.charAt(0) != query.charAt(0)) {
					continue;
				} else {
					if (query.length() == 2) {
						flag = true;
					} else {
						if (query.length() - 1 > next.length())
							continue;
						for (int i = 1; i < query.length() - 1; i++) {
							if (query.charAt(i) == next.charAt(i)) {
								flag = true;
							} else {
								flag = false;
								break;
							}
						}
					}
					if (flag)
						jokerArr.add(next);
				}
			}
		}
		return jokerArr;
	}

	public int countWords() {
		return dict.size();
	}

public static void main(String[] args) throws IOException {
	SearchDictionary dict = new SearchDictionary();
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     File f = new File("D:\\Readme.txt");
     BufferedReader fin = new BufferedReader(new FileReader(f));
     String name;
     String line;
     while ((line = fin.readLine()) != null) { 
    	 for (String retval : line.split(" ")) {
    		 String done="";
    		 for(int i=0;i< retval.length();i++) {
    			
    			 if(Character.isLetter( retval.charAt(i))){
    				done+=retval.charAt(i);
    			 }
    		 }
    		 if(!done.equals("")) {
    		 dict.addWord(done);
             System.out.println(done);
    		 }
         }
 }
     while(true) {
    	 System.out.println("Enter searching word"); 
     name = br.readLine();
     if(name.charAt(name.length()-1)=='*') {
     System.out.println(dict.query(name));
     }
     else {
    	 System.out.println("Has it word ("+name+")= "+dict.hasWord(name)); 
     }
}
}
}
