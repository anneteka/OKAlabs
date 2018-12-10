import java.util.Arrays;
import java.util.Scanner;

public class Wormix {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Mission[] missionArray = new Mission[s.nextInt()];
		double k = s.nextDouble();
		for (int i = 0; i < missionArray.length; i++) {
			missionArray[i] = new Mission(s.nextDouble(), s.nextDouble());
		}
		Arrays.sort(missionArray);

		double sumPoints = 0, sumTime = 0;
		for (int i = missionArray.length - 1; i > -1; i--) {
			sumPoints += missionArray[i].points;
			sumTime += missionArray[i].time;
			if (sumPoints >= k) {
				System.out.println((int) (sumTime));
				return;
			}
		}
		System.out.println(-1);
	}
}

class Mission implements Comparable<Mission> {
	public double points, time, pointsPerTime;

	public Mission(double points, double time) {
		this.time = time;
		this.points = points;
		this.pointsPerTime = points / time;
	}

	@Override
	public int compareTo(Mission that) {
		if (this.pointsPerTime > that.pointsPerTime)
			return 1;
		if (this.pointsPerTime < that.pointsPerTime)
			return -1;
		if (this.time > that.time)
			return -1;
		if (this.time < that.time)
			return 1;
		if (this.points > that.points)
			return 1;
		if (this.points < that.points)
			return -1;
		return 0;
	}
}