import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class telefonNum3 {
	static int i;
	static int n;
	int t;
	static boolean flag;
	static char number[] = new char[10];

	static class item {
		int leaf;
		int[] next = new int[10];

		item() {
			for (int i = 0; i < 10; i++)
				next[i] = -1;
			leaf = 0;
		}
		
	}

	static item[] trie;

	static void resize(int size){
		trie=new item[1];
	}
	
	static void insert(char[] s) {
		int i, v = 0;
		item temp = new item();
		if (flag== true)
			return;

		for (i = 0; i < s.length; ++i) {
			
			if (trie[v].leaf != 0) {
				flag = true;
				return;
			}
			char c = (char) (s[i] - '0');

			if (trie[v].next[c] == -1) {
				trie[v].next[c] = trie.length;
				trie[c] = temp;
			}
			v = trie[v].next[c];
		}
		for (i = 0; i < 10; i++)
			if (trie[v].next[i] != -1)
				flag = true;

		if (trie[v].leaf==1)
			flag = true;

		trie[v].leaf = 1;
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while (t>0)
		{
			n=sc.nextInt();

			trie=new item[1];
			flag = false;

			int[] phones=new int[n];
			
			for(int j=0;j<n;j++){
				phones[j]=sc.nextInt();
				
			}
			
			for (i = 0; i < n; i++)
			{
				insert(String.valueOf(phones[i]).toCharArray());
			}
			System.out.println(flag ? "NO\n" : "YES\n");
		}
	}
}
