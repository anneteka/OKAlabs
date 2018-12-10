public class Percolation{
	public int[][] id;
	public Percolation(int n){
		id=new int[n+1][n];
		for(int i=0;i<n+1;i++){
			for(int j=0;j<n;j++){
				if(i==0){
					id[i][j]=0;
					break;
				}
				id[i][j]=-1;
			}
			
		}
	}
	public int getOpenedCount(int n){
		int counter=0;
		for(int i=1;i<n+1;i++){
			for (int j=0;j<n;j++){
				if(id[i][j]!=-1){
					counter++;
				}
			}
		}
		return counter;
	}
	
	public int getZeroCount(int n){
		int counter=0;
		for(int i=1;i<n+1;i++){
			for (int j=0;j<n;j++){
				if(id[i][j]==0){
					counter++;
				}
			}
		}
		return counter;
	}
	
	public void open(int i,int j){
		id[i][j]=i+j;
	}
	public boolean isOpened(int i,int j){
		if (id[i][j]!=-1)
			return true;
		else
			return false;
	}
	public boolean percolates(int n){
		for(int i=0;i<n;i++){
			if(id[n][i]==0){
				return true;
			}
		}
		return false;

	}


	public boolean connected(int i1,int j1, int i2,int j2){
		return id[i1][j1]==id[i2][j2];
	}

	/*
	 * ���������������, ������ ������������������ ��������� ������'������������ p ������ q ���������'���������������������
	 */
	public int find(int i,int j){
		return id[i][j];
	}

	public void union(int i1, int j1,int i2,int j2,int n){
		int pid = find(i1,j1);
		int qid = find(i2,j2);
		if (pid==qid) return;
		for (int i=0; i<n+1;i++)
			for (int j=0;j<n;j++){
				//System.out.println(id[i][j].number+"    "+pid.number+"    "+qid.number);
				if (id[i][j]==pid)
					id[i][j] = qid;
				if(i==0||i==n+1)
					break;
			}

	}



}