import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Compare {
	public static void main(String[] args) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("input.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		String[] lineArray = new String[3];
		int num = 0;
		try {
			num = Integer.parseInt(in.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Superhero[] array = new Superhero[num];
		int j=0;
		try {
			while ((line = in.readLine()) != null) {
				lineArray = line.split(", ");
				array[j] = new Superhero(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]));
				j++;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Superhero superhero : array) {
			System.out.println(superhero);
		}
		System.out.println();
		
		
		ShellSort.sort(array);
		System.out.println("Sorted by names(unstable):");
		for (Superhero superhero : array) {
			System.out.println(superhero);
		}
		System.out.println();
		
		System.out.println("Sorted by powerlevel(unstable):");
		ShellSort.sort(array, array[0].POWER_ORDER);
		for (Superhero superhero : array) {
			System.out.println(superhero);
		}
		System.out.println();

		System.out.println("Sorted by country(unstable):");
		ShellSort.sort(array, array[0].COUNTRY_ORDER);
		for (Superhero superhero : array) {
			System.out.println(superhero);
		}
		System.out.println();
		
		
		MergeSort.sort(array);
		System.out.println("Sorted by names(stable):");
		for (Superhero superhero : array) {
			System.out.println(superhero);
		}
		System.out.println();
		
		System.out.println("Sorted by powerlevel(stable):");
		MergeSort.sort(array, array[0].POWER_ORDER);
		for (Superhero superhero : array) {
			System.out.println(superhero);
		}
		System.out.println();

		System.out.println("Sorted by country(stable):");
		MergeSort.sort(array, array[0].COUNTRY_ORDER);
		for (Superhero superhero : array) {
			System.out.println(superhero);
		}
		System.out.println();
	}
}

class Superhero implements Comparable<Superhero> {
	String name;
	String country;
	int power_level;

	public final Comparator<Superhero> POWER_ORDER = new PowerOrder();
	public final Comparator<Superhero> COUNTRY_ORDER = new CountryOrder();

	public Superhero(String name, String country, int power_level) {
		this.name = name;
		this.country = country;
		this.power_level = power_level;
	}

	private class PowerOrder implements Comparator<Superhero> {
		public int compare(Superhero p, Superhero q) {
			if (p.power_level < q.power_level)
				return -1;
			if (p.power_level > q.power_level)
				return +1;
			return 0;
		}
	}

	private class CountryOrder implements Comparator<Superhero> {
		public int compare(Superhero p, Superhero q) {
			return p.country.compareTo(q.country);
		}
	}

	@Override
	public int compareTo(Superhero that) {
		return this.name.compareTo(that.name);
	}

	public String toString() {
		return name + "; " + country + "; " + power_level;

	}
}
