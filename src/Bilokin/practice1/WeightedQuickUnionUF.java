

public class WeightedQuickUnionUF {

	private int[] id;
	private int[] sz; // розмір компонента для коренів 
	private int count;
	
	public WeightedQuickUnionUF(int n){
		count = n;
		id = new int[n];
		sz = new int[n];
		for (int i = 0; i<n; i++)
			id[i]=i;
		for (int i = 0; i<n; i++)
			sz[i] = 1;
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	private int find(int p){
		while(p != id[p])
			p=id[p];
		return p;
	}
	
	public void union(int p, int q){
		int i = find(p);
		int j = find(q);
		if (i==j) return;
		if (sz[i]<sz[j]){
			id[i] = j;
			sz[j] +=sz[i];
		}else{
			id[j] = i;
			sz[i] += sz[j]; 
		}
		count--;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		for (int i:id){
			str.append(i);
			str.append(" ");
		}
		return str.toString();
	}
}
