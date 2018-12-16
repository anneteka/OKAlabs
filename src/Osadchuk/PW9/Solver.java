package PW9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solver {
	
	PriorityQueue<Board> pq;
	ArrayList<Board> solutions;
	Board startingBoard;
	
	public Solver(Board initial) {
		this.startingBoard = initial;
		this.pq = new PriorityQueue<Board>();
		pq.add(startingBoard);
		
		this.solutions = new ArrayList<Board>();
		// знайти рішення для дошки initial
	}
	
	public boolean isSolvable() {
		return true;
	}
	
	public int moves() {
		if(!isSolvable())
			return -1;
		
		int counter = -1;
		
		if(startingBoard.isGoal()) {
			solutions.add(startingBoard);
			return 0;
		}
		
		Board bd = startingBoard;
		do {
			bd = pq.poll();
			pq.clear();
			
			solutions.add(bd);
			for(Board b: bd.neighbors()) {
				pq.add(b);
			}
			
			++counter;
		} while(!bd.isGoal());
		
		return counter;
		// мінімальна кількість кроків для вирішення дошки, -1 якщо немає рішення
	}
	
	public Iterable<Board> solution() {
		if(!isSolvable())
			return null;
		
		return this.solutions;
		// послідовність дошок в найкоротшому рішенні; null якщо немає рішення
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// створюємо початкову дошку з файлу
		Scanner sc = new Scanner(new File("C:/Users/Lenovo/Desktop/puzzle04.txt"));
		int N = Integer.parseInt(sc.nextLine());
		
		int[][] blocks = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] sts = sc.nextLine().split(" ");
				for (int j = 0; j < N; j++)
					blocks[i][j] = Integer.parseInt(sts[j]);
			}
			
		Board initial = new Board(blocks);
		
		Solver solver = new Solver(initial);
		
		if (!solver.isSolvable())
			System.out.println("Дошка не має розв’язку");
		else {
			System.out.println("Мінімальна кількість кроків = " + solver.moves());
			for (Board board : solver.solution())
				System.out.println(board.toString());
		}
	}
	
}