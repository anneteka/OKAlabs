import java.io.FileNotFoundException;

public class Tester {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "/Users/michaelfilonenko/LocalDocs/KMA/ОКА/P5/films.txt";
        Film[] films = Reader.readData(path);

        for(int i=0;i<films.length;i++) {
            System.out.println(films[i].toString());
        }
        System.out.println("\n");
        ShellSort.sort(films, Film.BY_NAME);
        for(int i=0;i<films.length;i++) {
            System.out.println(films[i].toString());
        }
        // first sort is stable
        // merge sort is stable
        // insertion sort is stable
        // selection sort
        // shell sort is stable
        // selection sort is not stable
    }
}
