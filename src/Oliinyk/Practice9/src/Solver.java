import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class Solver {
	
	// знайти рішення для дошки initial
	private class Node implements Comparable<Node>{
		Board currentBoard;
		Node preNode;
		int counter;
		public Node(Board currentBoard, int counter, Node preBoard){
			this.currentBoard=currentBoard;
			this.preNode=preBoard;
			this.counter=counter;
		}
		@Override
		public int compareTo(Node n) {
//			int hThis=counter+currentBoard.manhattan();
//			int h=n.counter+n.currentBoard.manhattan();
			int hThis=counter+currentBoard.hamming();
			int h=n.counter+n.currentBoard.hamming();
	    	if(hThis==h) return 0;
	    	else if(hThis<h) return -1;
	    	else return 1;
		}
	}
	MinPQ<Node> pq;
	Node goal;
	Board first;
    public Solver(Board initial) {
    	pq=new MinPQ<Node>(1);
    	first=initial;
    	if(isSolvable()) {
    	Node n=new Node(initial, 0, null);
    	pq.insert(n);
    	goal=go();
    	}
    }
 private Node go() {
	 Node cur=pq.delMin();
	 int i = 0;
	   while(!cur.currentBoard.isGoal()) {
//		  if(i>60)
//		   break;
//		   StdOut.println(cur.currentBoard);
//		   StdOut.println(cur.currentBoard.hamming());
		   for(Board b:cur.currentBoard.neighbors()) {
			  if( cur.preNode==null) pq.insert(new Node(b, cur.counter+1, cur));
			  else  if(!b.equals(cur.preNode.currentBoard) ) 
				   pq.insert(new Node(b, cur.counter+1, cur));
		   }
		   cur=pq.delMin();
		   i++;
	   }
	  return cur;
	}
// чи має початкова дошка розв’язок
    public boolean isSolvable() {
    	if(first.hamming()%2!=0 || first.hamming()==8) return true;
   	else return false;
    	  
    	
    }
    
 // мінімальна кількість кроків для вирішення дошки, -1 якщо немає рішення
    public int moves() {
		if(isSolvable())return goal.counter;	
		else return -1;
    }
  	// послідовність дошок в найкоротшому рішенні; null якщо немає рішення
    public Iterable<Board> solution(){
    	if(isSolvable()) {
    	LinkedList<Board> l = new LinkedList<Board>();
    	Node cur=goal;
    	while(cur!=null) {
    		l.add(cur.currentBoard);
    		cur=cur.preNode;
    	}
		return l;
    	} else return null;
  
    }
 // вирішити
    public static void main(String[] args) throws IOException {
        // створюємо початкову дошку з файлу
        In in = new In("/puzzle3x3-unsolvable.txt");
       // In in = new In("/puzzle04.txt");
        //In in = new In("/puzzle31.txt");
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        // розв’язати
        Solver solver = new Solver(initial);

        // надрукувати рішення
       if (!solver.isSolvable())
            StdOut.println("Unsolvable");
        else {
            StdOut.println("Min = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    
}

