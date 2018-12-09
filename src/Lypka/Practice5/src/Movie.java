import java.util.Comparator;

public class Movie implements Comparable<Movie>{

	public final Comparator<Movie> BY_GENRE = new ByGenre();
	public final Comparator<Movie> BY_YEAR = new ByYear();
	public final Comparator<Movie> BY_RATING = new ByRating();
	
	private String name;
	private String genre;
	private int releaseYear;
	private double rating;

	public Movie(String name, String genre, int releaseYear, double rating) {
		this.name = name;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public double getImdbRating() {
		return rating;
	}

	public void setImdbRating(int imdbRating) {
		this.rating = imdbRating;
	}

	@Override
	public int compareTo(Movie that) {
		return this.name.compareToIgnoreCase(that.name);
	}
	
	private class ByGenre implements Comparator<Movie>{
		@Override
		public int compare(Movie m1, Movie m2) {
			return m1.genre.compareToIgnoreCase(m2.genre);
		}		
	}
	
	private class ByYear implements Comparator<Movie>{
		@Override
		public int compare(Movie m1, Movie m2) {
			if(m1.releaseYear < m2.releaseYear) return -1;
			if(m1.releaseYear > m2.releaseYear) return 1;
			return 0;
		}		
	}
	
	private class ByRating implements Comparator<Movie>{
		@Override
		public int compare(Movie m1, Movie m2) {
			if(m1.rating < m2.rating) return 1;
			if(m1.rating > m2.rating) return -1;
			return 0;
		}	
	}
	
	public String toString() {
		return name + ", " + releaseYear + ", " + genre + ", IMDB rating: " + rating;
	}
}
