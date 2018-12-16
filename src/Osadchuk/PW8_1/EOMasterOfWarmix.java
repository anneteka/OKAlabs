package PW8_1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class EOMasterOfWarmix {
	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String st[] = reader.readLine().split(" ");
		
		int n = Integer.parseInt(st[0]);// <100
		int k = Integer.parseInt(st[1]);// <10000
		int newSize = n;
		
		Ochko[] kk = new Ochko[n];
		for (int i = 0; i < n; ++i) {
			String numbers[] = reader.readLine().split(" ");
			Ochko oo = new Ochko(Byte.parseByte(numbers[0]), Byte.parseByte(numbers[1]));
			boolean isCont = false;
			for (int j = i-1; j >=0; --j)
				if(kk[j]!=null && oo.hash==kk[j].hash) {
					++kk[j].counts;
					isCont = true;
					--newSize;
					break;
				}
			if(!isCont) {
				kk[i] = oo;
			}
		}
		
		Ochko[] ok = new Ochko[newSize];
		int next = 0;
		for(int i=0; i< n; ++i) {
			if(kk[i]!=null)
				ok[next++] = kk[i];
		}
		
		int answ = 0;
		
		int f = 0;
		int[]func = new int[k+1];
		int tt = 0;
		
		while(f++!=n) {
			
			for(int i=0; i<=k; ++i) {
				int max = 0;
				
				for(int quantity=0; quantity<=ok[i].counts; ++quantity) {
					int mx = quantity*ok[i].point + func[i - ok[i].time*quantity];
					
					if(max<mx) {
						max = mx;
						tt = quantity;
					}
				}
				
				func[i] = max;
			}
			
			
		}
		
	}
}

class Ochko{
	
	int point, time, counts, hash;
	
	public Ochko(int p, int t) {
		this.point = p;
		this.time = t;
		this.counts = 1;
		this.hash = hashCode();
	}
	
	@Override
	public int hashCode() {
		return 100*this.point + this.time;
	}
	
}