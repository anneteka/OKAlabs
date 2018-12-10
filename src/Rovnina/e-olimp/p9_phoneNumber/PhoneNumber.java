package phoneNumber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PhoneNumber {
	
	/**
	 * class for creating Node of Prefix Tree
	 *
	 */
	static class Node{
		Node[] keys = new Node[10];
		boolean exist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/phoneNumber/input.txt"));
		PrintWriter pw = new PrintWriter(new File("src/phoneNumber/output.txt"));

		String result = "";
		int nTests =  Integer.parseInt(br.readLine());
		for (int t = 0; t < nTests; t++) {
			int nLines = Integer.parseInt(br.readLine());//sc.nextInt();
			// New root for test
			Node root = new Node();
			result = "YES";
			for (int i = 0; i < nLines; i++) {
				String line = br.readLine();
				if( result.equals("NO"))
					continue; // we must read all lines to reach next test
				// Operate new line
				Node cur = root;
				for (int j = 0; j < line.length(); j++) {
					// Operate current symbol
					int k = line.charAt(j) - 48;
					Node marker = cur.keys[k];
					if(marker == null){
						// No such sequences early
						marker = new Node();
						cur.keys[k] = marker;
					}
					else{
						if(marker.exist){
							// Such number was early
							result = "NO";	
							break;
						}
					}
					// To next node
					cur = marker;		
				}	
				// Indicate new number
				cur.exist = true;
				// Test if this number is part of another number
				for (Node node : cur.keys) {
					if(node != null ){
						result = "NO";
						break;					}
				}
			}
			pw.printf("%s\n",result);
		}
		br.close();
		pw.close();	
	}
}
