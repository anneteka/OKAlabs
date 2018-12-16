import java.util.Comparator;
import java.util.Date;

public class Film implements Comparable<Film>{
   private String name;
  private int id;
  private  Date releaseDate;
 private   String[] actors;
    public static final Comparator<Film> BY_NAME = new FilmNameComparator();
    public static final Comparator<Film> BY_DATE = new FilmDateComparator();
    public static final Comparator<Film> BY_ACTORS = new FilmActorsComparator();
    public static final Comparator<Film> BY_ID = new FilmIDComparator();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    @Override
    public int compareTo(Film that) {
        return this.name.compareTo(that.name);
    }

    private static class FilmNameComparator implements  Comparator<Film> {

        @Override
        public int compare(Film o1, Film o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    private static class FilmDateComparator implements  Comparator<Film> {

        @Override
        public int compare(Film o1, Film o2) {
            return o1.getReleaseDate().compareTo(o2.getReleaseDate());
        }
    }
    private static class FilmActorsComparator implements  Comparator<Film> {

        @Override
        public int compare(Film o1, Film o2) {
            return o1.getActors().length - o2.getActors().length;
        }
    }
    private static class FilmIDComparator implements  Comparator<Film> {

        @Override
        public int compare(Film o1, Film o2) {
            return o1.getId() - o2.getId();
        }
    }
    public String toString() {
        String result = name + " " + id + " " + releaseDate + " " ;
        for(String actor: actors) {
            result += actor + " ";
        }
        return result;
    }
}
