import java.io.*;

import javax.sound.midi.SysexMessage;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;
/*Завантажити файл Timing.java.
Написати програму, що викликає Timing.trial(N, 777280) 
для різних значень N, виміряти скільки часу займає виклик даного методу 
(використовувати Stopwatch).
Сформулювати гіпотезу порядку зростання.
T(N) ~aN^b. Ви маєте надати значення a та b.*/
public class TimingTest1 {

	private static int n,counter=0;
			private static double b=1,sum=0;
	public static void main(String[] args) {
		for(int i=8;i<60;i+=6) {
		n=3000*i;
		counter++;
		
		System.out.println("n: "+n);
		Stopwatch stopwatch = new Stopwatch();
		Timing.trial(n, 777280);
		double time = stopwatch.elapsedTime();
		sum+=(time/Math.pow(n,2))*Math.pow(10,10);
		b=1.924565;
		System.out.println("Витрачений час "+time);
		}
		System.out.println(b);
		double ave=sum/counter;
		System.out.println("b~1.92 \na~"+ave+" 10^-10");
		System.out.println("T = "+ave+"*10^-10 N^2");
	}

}
