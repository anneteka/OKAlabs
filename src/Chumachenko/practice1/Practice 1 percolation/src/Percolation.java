import java.util.Random;

public class Percolation {
	int count;
    boolean[] system;
    int[] systemIndex;
    WeightedQuickUnionUF uf;
    int size;
	/** Конструктор 
	 * @param n - розмір сітки
	 */
	public Percolation(int n) {
		//uf= new QuickFindUF(n*n+2);
		uf = new WeightedQuickUnionUF(n*n+2);
	//	System.out.println(uf.toString());
		size = n;
		int sLength = n*n+2;
		system = new boolean[sLength];
		/* 
		 * the first element, unreal
		 */
		system [0] = true;
		for(int i = 1; i<=sLength-1;i++) {
			system [i] = false;
		}
		/*
		 * the last one
		 */
		system[system.length-1] = true;
		
		/*
		 * Create matrix NxN 
		 * with blocked elements
		 * 
		 */
		unitTopBottom();
	}
	
	private void unitTopBottom() {
		
		
	for(int i = 1; i<size; i++ )
		if(system[i])uf.union(0, i);       
	for(int j = system.length-size; j<system.length; j++ ) 
		if(system[j-1])uf.union(system.length-1, j);
	
	
	}
	public int getOpenedCount() {
		/*
		 * Calculate unblocked elements 
		 * return number of unblocked
		 * 
		 */
		
		return count;
	}
	public void open(int i, int j) {
		
		/* 
		 * if object (row i, column j) are blocked,
		 * open it 
		 * 
		 */
		int index = (i-1)*size+j;
		if(!system [index]) {
			
		system [index] = true;
		count++;
		
		checkPosUnion(index, i, j);
		}
		
	}
	
	private void checkPosUnion(int index, int i, int j) {
	if(i!=1&&i!=size) {
		if(system[index-size]) {
			uf.union(index, index-size);
		}
		if(system[index+size]) {
			uf.union(index, index+size);
		}
	}
	else if(i==1) {
		
		uf.union(index, 0);
		
		if(system[index+size]) {
			uf.union(index, index+size);
		}
	}
	else {
		uf.union(index,system.length-1 );
		if(system[index-size]) {
		uf.union(index, index-size);
	
	}
	}
	
	if(j!=1&&j!=size) {
		if(system[index+1]) uf.union(index, index+1);
		if(system[index-1]) uf.union(index, index-1);
		}
	else if(j==1) {
		if(system[index+1]) uf.union(index, index+1);
	}
	else if(system[index-1]) uf.union(index, index-1);

	}
	public boolean percolates() {
		
		return uf.connected(0, system.length-1);
		
	}
	
	public void opening() {
		Random random = new Random();
		while (! percolates ()) {
			int i = random.nextInt(size)+1;
			int j = random.nextInt(size)+1;
		
			open(i,j);
			
		}
	}

	public String toString() {
	String line ="";
	for(int i = 0; i<system.length; i++) {
		line+=i+")  "+system[i];
		//System.out.println("HR: "+i+" "+system[i]);
	}
	
		return line;
		
	}
}
