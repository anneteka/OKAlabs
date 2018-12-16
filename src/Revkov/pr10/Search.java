package a;

import java.util.ArrayList;

public class Search {

	private Node root;

	private class Node {
		private char firstLetter;
		private ArrayList<String> arr=new ArrayList<>();
		private Node left, right;

	}

	public Search() {

	}

	public void add(String word) {
		if (root == null) {
			root = new Node();
			root.firstLetter = word.charAt(0);
			root.arr.add(word);
		} else {
			findNeededNode(root, word.charAt(0),word,"add");
		}	
	}
	
	public void delete(String word){
		if(root==null)System.out.println("root is undefined");
		else findNeededNode(root, word.charAt(0), word, "delete");
	}
	
	public void find(String word){
		if(root==null)System.out.println("root is undefined");
		else {
			if(word.charAt(word.length()-1)=='*'){
				word=word.substring(0, word.length()-1);
				findNeededNode(root, word.charAt(0), word, "findAll");
			}
			else findNeededNode(root, word.charAt(0), word, "find");
		}
	}
	

	public void findNeededNode(Node node, char c,String word,String event) {
		int flag=-1;
		Node n = null;
		while(true){
			
			if(node!=null&&node.firstLetter>c){
				flag=0;
				n=node;
				node=node.left;
			}
			else if(node!=null&&node.firstLetter<c){
				flag=1;
				n=node;
				node=node.right;	
			}
			else if(node!=null&&node.firstLetter==c){
				if(event.matches("add")&&!ifContains(node.arr, word))node.arr.add(word);
				else if(event.matches("delete")) {
					if(getIndex(node.arr, word)>=0)node.arr.remove(getIndex(node.arr, word));
				}
				else if(event.matches("findAll")){
					printAllMatchedResults(node.arr, word);
				}
				else if(event.matches("find")){
					printOneWord(node.arr, word);
				}
				break;
			}
			else {
				if(event.matches("add")){
					node = new Node();
					node.firstLetter = c;
					node.arr.add(word);
					if (flag == 0)
						n.left = node;
					else if (flag == 1)
						n.right = node;
				}
				
				break;
			}
		}
	}
	
	private void printOneWord(ArrayList<String> arr,String str){
		boolean flag=false;
		for(String i:arr){
			if(i.matches(str)){
				flag=true;
				System.out.println(str);
				break;
			}
		}
		if(!flag)System.out.println("No such word");
	}
	
	private void printAllMatchedResults(ArrayList<String> arr,String str){
		for(String i:arr){
			if(i.startsWith(str))System.out.println(i);
		}
	}
	
	private boolean ifContains(ArrayList<String> arr,String str){
		for(String i:arr){
			if(i.matches(str))return true;
		}
		return false;
	}
	
	private int getIndex(ArrayList<String> arr,String str){
		int k=0;
		for(String i:arr){
			if(i.matches(str))return k;
			k++;
		}
		return -1;
	}

	public static void main(String[] args) {
		Search s = new Search();
		char k='p';
		char n='s';
		s.add("Privet");
		s.add("Gndrey");
		s.add("Volodya");
		s.add("Albert");
		s.add("Avakado");
		s.delete("Vova");
		s.find("Gndrey");
		s.find("Alkash");
		s.add("Kiev");
		s.add("Kievskiy");
		s.add("Kievcool");
		s.add("Kyiv");
		s.find("Kiev*");
	}
}
