import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Reader {
    public static final Film[] readData(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        ArrayList<Film> films = new ArrayList<Film>();
        while (scanner.hasNext()) {
            String name = scanner.nextLine();
            int id = scanner.nextInt(); scanner.nextLine();
            int date = scanner.nextInt(); scanner.nextLine();
            String actors = scanner.nextLine();
            Film film = new Film();
            film.setId(id);
            film.setName(name);
            film.setActors(actors.split(" "));
            film.setReleaseDate(new Date(date));
            films.add(film);
        }
        return films.toArray(new Film[films.size()]);
    }
}
