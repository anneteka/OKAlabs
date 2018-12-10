import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class PhoneNUmber3 {
	static int i;
	static int n;
	int t;
	static boolean flag;
	static char number[] = new char[10];

	class item {
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

		// Отмечаем факт завершения обработки слова s в вершине trie[v].
		trie[v].leaf = 1;
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while (t>0)
		{
			n=sc.nextInt();

			//Чистим бор. Устанавливаем его длину равной единице : это единственная вершина – корень бора.
			//Изначально все телефонные номера считаются совместимыми, присваиваем переменной flag значение 0.
//			trie.clear();
//			trie.resize(1);
			trie=new item[1];
			flag = false;

			int[] phones=new int[n];
			
			for(int j=0;j<n;j++){
				phones[j]=sc.nextInt();
				
			}
			
			//Читаем телефонные номера. Заносим их в бор. Даже если на каком - то этапе обнаружена несовместимость
			//номеров – все равно следует дочитать номера до конца, так как входные данные содержат несколько тестов.
			for (i = 0; i < n; i++)
			{
				insert(String.valueOf(phones[i]).toCharArray());
			}
			//В зависимости от значения flag выводим ответ.
			System.out.println(flag ? "NO\n" : "YES\n");
		}
	}
}
