import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tester {

	
	public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("src/Data/DogTester.txt"));
	StringTokenizer st = new StringTokenizer(br.readLine());	
	int n = Integer.valueOf(st.nextToken());
	Dog[] dogs = new Dog[n];
		for(int i = 0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			dogs[i] = new Dog(st.nextToken(),  st.nextToken(), Integer.valueOf(st.nextToken()));
		}
		
		for(Dog g: dogs) {
			System.out.println(g);
		}
		System.out.println();
		MergeSort.sort(dogs, dogs[0].SortedByName);
		for(Dog g: dogs) {
			System.out.println(g);
		}
		
		System.out.println();
		MergeSort.sort(dogs, dogs[0].SortedByAge);
		for(Dog g: dogs) {
			System.out.println(g);
		}
		
		System.out.println();
		MergeSort.sort(dogs, dogs[0].SortedByBreed);
		for(Dog g: dogs) {
			System.out.println(g);
		}
		
		System.out.println();
	
		
	}
}
