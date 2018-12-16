import java.util.Arrays;

public class Perculation {
	private WeightedQuickUnionUF unionFindAlgorithm;
	private boolean[] openCells;
	private int numberOfRows;
	private int openedCount =0;
	public Perculation(int numberOfRows) {
		this.numberOfRows = numberOfRows;
		int numberOfCells =numberOfRows*numberOfRows;
		unionFindAlgorithm = new WeightedQuickUnionUF(numberOfCells);
		unionTopAndBottomCells();
		openCells = new boolean[numberOfCells];
		Arrays.fill(openCells, false);
	}
	private int cellNumber(int row, int column) {
		// get cell number from row and column
		return row*numberOfRows + column;
	}
	private void unionTopAndBottomCells() {
		// is called in the method constructor to prepare a virtual sets of top and bottom cells
		for(int i=0;i<numberOfRows-1;i++) {
			unionFindAlgorithm.union(i, i+1);
			unionFindAlgorithm.union(numberOfRows*numberOfRows-1-i, numberOfRows*numberOfRows-2-i);
		}
	}
	private int[] neighborsOfCell(int row, int column) {
		// 0 top 1 right 2 bottom 3 left
		return Arrays.stream(new int[] {row == 0 ? -1 : cellNumber(row, column) - numberOfRows,
				column == numberOfRows-1 ? -1 : cellNumber(row, column)+1, 
				row == numberOfRows-1 ? -1 : cellNumber(row,column)+numberOfRows, 
				column == 0 ? -1 : cellNumber(row,column)-1})
				.filter(x -> x!=-1).toArray();
	}
	public void openCell(int row, int column) {
		if(isOpened(row,column)) return;
		openedCount++;
		openCells[cellNumber(row,column)] = true;
		for(int neighbor: neighborsOfCell(row,column)) {
			if(openCells[neighbor])
			unionFindAlgorithm.union(neighbor, cellNumber(row,column));
		}
	}
	public boolean perculates() {
		return unionFindAlgorithm.connected(0, numberOfRows*numberOfRows-1);
	}
	public int getOpenedCount() {
		return openedCount;
	}
	public boolean isOpened(int row,int column) {
		return openCells[cellNumber(row,column)];
	}
}
