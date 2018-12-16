package week12.lection;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebCrawler {

	Queue<String> queue = new Queue<String>();
	SET<String> marked = new SET<String>();
	private int depth;
	
	public WebCrawler(String root, int depth){
		queue.enqueue(root);
		marked.add(root);
		this.depth=depth;
	}

	public void process() {
		while((!queue.isEmpty())&&(marked.size()<=depth)){
			String v = queue.dequeue();
			StdOut.println(v);
			In in = new In(v);
			if (!in.exists()) continue;
			String input = in.readAll();
			if (input == null) continue;
			String regexp = "http://(\\w+\\.)*(\\w+)";
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(input);
			while(matcher.find()){
				String w = matcher.group();
				if (!marked.contains(w)){
					marked.add(w);
					queue.enqueue(w);
				}
			}
		}
		
	}
	
	public static void main(String[] args){
		WebCrawler crawler = new WebCrawler("http://www.bigmir.net",1000);
		crawler.process();
	}
}
