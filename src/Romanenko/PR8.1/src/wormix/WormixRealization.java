package wormix;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class WormixRealization {

	public int minTime(Mission[] mission, int points) {
		int time = 0;
		int allpoints = 0;
		int minTime = 0;
		for (int i = 0; i < mission.length; i++) {
			allpoints += mission[i].getPoints();
			time += mission[i].getTime();
		}

		Arrays.sort(mission);
		for (int i = 0; i < mission.length; i++) {
			if (allpoints - mission[i].getPoints() >= points) {
				allpoints -= mission[i].getPoints();
				time -= mission[i].getTime();
			}

		}
		minTime = time;
		return minTime;

	}
	public static void main(String[] args) throws FileNotFoundException {
		String filename = "worm.txt";
		Scanner s = new Scanner(new FileInputStream(filename));
		int row = s.nextInt();
		int point = s.nextInt();
		Mission[] m = new Mission[row];
		for(int i = 0; i < row; i++){
			 m[i] = new Mission(s.nextInt(), s.nextInt());
		}
		s.close();
		
		WormixRealization wormix = new WormixRealization();
		System.out.println(wormix.minTime(m, point));
		
	}

}
