package PW9;

import java.util.ArrayList;

public class Board implements Comparable<Board> {
	
	private int[][] blocks;
	private int len;
	
	public Board(int[][] blocks) {
		this.blocks = blocks;
		this.len = blocks.length;
	}
	// (blocks[i][j] =блок в ряду i, колонці j)

	public int dimension() {
		return len;
		// розмірність дошки N
	}

	public int hamming() {
		int res = 0;
		
		for (int i = 0; i < len; ++i)
			for (int j = 0; j < len; ++j)
				if(blocks[i][j]!=(i*len+j+1)) {
					++res;
				}
		if(blocks[len-1][len-1]==0)
			--res;
		
		return res;
		// кількість блоків не на своєму місці
	}

	public int manhattan() {
		int res = 0;
		
		for (int i = 0; i < len; ++i)
			for (int j = 0; j < len; ++j)
				if(blocks[i][j]!=(i*len+j+1))
					if(blocks[i][j]!=0) {
						int needJ = (blocks[i][j]-1)/len;
						int needI = (blocks[i][j]-1) - needJ*len;
						res+= (Math.abs(needJ-i) + Math.abs(needI-j));
					} else {
						res+= 2*(len-1)-j-i;
					}
		return res;
		// сума Манхатенських відстаней між блоками і цільовим станом
	}
	
	public boolean isGoal() {
		return hamming()==0;
		// чи є ця дошка цільовим станом
	}

	@Override
	public boolean equals(Object y) {
		Board br;
		try {
			br = (Board)y;
		} catch (ClassCastException e) {
			return false;
		}
		
		for (int i = 0; i < len; ++i)
			for (int j = 0; j < len; ++j) {
				if(blocks[i][j]!=br.blocks[i][j])
					return false;
			}
		
		return true;
		// чи ця дошка рівна y?
	}

	public Iterable<Board> neighbors() {
		ArrayList<Board> res = new ArrayList<Board>(4);
		
		int i=0, j=0;
		cikl:
		for (i = 0; i < len; ++i)
			for (j = 0; j < len; ++j)
				if(blocks[i][j]==0) {
					break cikl;
				}
		
		if(j<len-1) {
			int[][] copy = new int[len][len];
			for(int k=0; k<len; ++k)
				for(int l=0; l<len; ++l) {
					copy[k][l] = blocks[k][l];
				}
			
			copy[i][j] = blocks[i][j+1];
			copy[i][j+1] = 0;
			res.add(new Board(copy));
		}
		if(j>0) {
			int[][] copy = new int[len][len];
			for(int k=0; k<len; ++k)
				for(int l=0; l<len; ++l) {
					copy[k][l] = blocks[k][l];
				}
			copy[i][j] = blocks[i][j-1];
			copy[i][j-1] = 0;
			res.add(new Board(copy));
		}
		if(i>0) {
			int[][] copy = new int[len][len];
			for(int k=0; k<len; ++k)
				for(int l=0; l<len; ++l) {
					copy[k][l] = blocks[k][l];
				}
			copy[i][j] = blocks[i-1][j];
			copy[i-1][j] = 0;
			res.add(new Board(copy));
		}
		if(i<len-1) {
			int[][] copy = new int[len][len];
			for(int k=0; k<len; ++k)
				for(int l=0; l<len; ++l) {
					copy[k][l] = blocks[k][l];
				}
			copy[i][j] = blocks[i+1][j];
			copy[i+1][j] = 0;
			res.add(new Board(copy));
		}
		
		return res;
		// всі сусдні дошки
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < len; ++i)
			for (int j = 0; j < len; ++j) {
				str+= blocks[i][j] + " ";
				if(j+1==len)
					str+= "\n";
			}
		return str;
	}
	
	public int compareTo(Board that) {
		if(this.manhattan()>that.manhattan())
			return 1;
		if(this.manhattan()<that.manhattan())
			return -1;
		return 0;
	}
	
}