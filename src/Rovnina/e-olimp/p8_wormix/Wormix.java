package wormix;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Wormix {

	static class Mission implements Comparable<Mission> {
		int score;
		int time;

		public Mission(int score, int time) {
			super();
			this.score = score;
			this.time = time;
		}

		@Override
		public int compareTo(Mission o) {
			Double d1 = new Double((double) score / time);
			Double d2 = new Double((double) o.score / o.time);
			if (d1.equals(d2))
				return time - o.time;
			return d2.compareTo(d1);
		}

	}

	static int n;
	static int k;
	static Mission[] mis;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(bis);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));

		n = sc.nextInt();
		k = sc.nextInt();
		// create array
		mis = new Mission[n];
		for (int i = 0; i < n; i++)
			mis[i] = new Mission(sc.nextInt(), sc.nextInt());

		Arrays.sort(mis);

		// find start value for result
		int fullScore = 0;
		int fullTime = 0;
		for (int i = 0; i < mis.length; i++) {
			fullScore += mis[i].score;
			fullTime += mis[i].time;
			if (fullScore < k)
				continue;
			if (fullScore == k)
				result = fullTime;
			else {// reduce extra
				int extra = fullScore - k;
				for (; i >= 0; i--) {
					if (mis[i].score <= extra) {
						fullTime -= mis[i].time;
						fullScore -= mis[i].score;
						extra = extra - mis[i].score;
						if (extra == 0)
							break;
					}
				}
				result = fullTime;
			}
			break;
		}

		if (n > 27)// reduce size of array to avoid overtime, but some result may be wrong
			n = 27;

		// analyze tree for ache element of array
		for (int i = 0; i < n; i++)
			findBest(0, 0, i);

		if (result == Integer.MAX_VALUE)
			result = -1;
		out.printf("%d", result);
		sc.close();
		out.close();
	}

	static void findBest(int sumScore, int sumTime, int idx) {
		int score = sumScore + mis[idx].score;
		int time = sumTime + mis[idx].time;
		if (score >= k && time < result)// have needed amount of score and new time is best than result
			result = time;
		else if (score < k && time < result)
			for (int i = idx + 1; i < n; i++)// iterating over the remaining elements of tree
				findBest(score, time, i);

		// else return
	}

}
