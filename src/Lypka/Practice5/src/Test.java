import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		File input = new File("input.txt");
		try {
			Scanner in = new Scanner(input);
			ArrayList<Movie> moviesArray = new ArrayList<>();
			while (in.hasNextLine()) {
				moviesArray.add(new Movie(in.nextLine(), in.nextLine(), Integer.parseInt(in.nextLine()), Double.parseDouble(in.nextLine())));
			}
			
			Movie[] movies = new Movie[moviesArray.size()];
			
			for(int i = 0; i < moviesArray.size(); i++)
				movies[i] = moviesArray.get(i);

			for (Movie m : movies)
				System.out.println(m);

			System.out.println("\n\nSorted by Name\n");
			Arrays.sort(movies);
			for (Movie m : movies)
				System.out.println(m);

			System.out.println("\n\nSorted by Genre\n");
			Arrays.sort(movies, movies[0].BY_GENRE);
			for (Movie m : movies)
				System.out.println(m);

			System.out.println("\n\nSorted by Release Year\n");
			Arrays.sort(movies, movies[0].BY_YEAR);
			for (Movie m : movies)
				System.out.println(m);

			System.out.println("\n\nSorted by IMDB Rating\n");
			Arrays.sort(movies, movies[0].BY_RATING);
			for (Movie m : movies)
				System.out.println(m);

			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
	}
}
