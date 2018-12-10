package queue;

public class Queue {
	private final int n;
	private final int k;
	private int time;
	private final int[] queue;
	private int[] cashiers;

	Queue(int n, int k, int[] queue) {
		this.n = n;
		this.k = k;
		this.queue = queue;
	}
	public int Station(){
		cashiers = new int [k];
		time = 0;
		int count = 0;
		while (count<n || !(paydeskIsFree(cashiers))){
			for (int i=0; i<cashiers.length; i++)
				if ((cashiers[i] == 0) && count < n){
					cashiers[i] = queue[count];
					count++;
				}
			for (int i=0; i<cashiers.length; i++)
				if (cashiers[i]>0) cashiers[i]--;
			time++;
		}
		return(time);

	}
	private boolean paydeskIsFree(int[] cashiers){
		for (int i=0; i<cashiers.length; i++)
			if (cashiers[i]>0) return false;
		return true;
	}

}
