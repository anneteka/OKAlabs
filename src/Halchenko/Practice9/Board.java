
public class Board {
	private int[][] board;
	public Board(int[][] blocks){
		this.board=blocks;
	}
	public int hamming(){
		
		int correctVal=1;
		int result=0;
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				if(board[i][j]==0){
					correctVal++;
					continue;
				}
				if(board[i][j]!=correctVal)result++;
				correctVal++;
			}
		}
		return result;
	}
	public int dimension(){
		return board.length;
	}
}
