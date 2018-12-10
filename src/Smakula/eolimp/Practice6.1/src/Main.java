import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    String[] dictionary;
    private int currentRow;

    public Main(int n) {
        dictionary = new String[n];
    }

    private int getInvCount(String word) {
        int inv_count = 0;
        for (int i = 0; i < word.length() - 1; i++)
            for (int j = i + 1; j < word.length(); j++)
                if (word.charAt(i) > word.charAt(j))
                    inv_count++;
        return inv_count;
    }

    private void add(String word) {
        dictionary[currentRow++] = word;
    }

    private void sort() {
        Arrays.sort(dictionary, (Comparator.comparingInt(this::getInvCount)));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int numberOfTests = Integer.parseInt(line[0]);
        for (int i = 0; i < numberOfTests; i++) {
            reader.readLine();
            line = reader.readLine().split(" ");
            int rowLenght = Integer.parseInt(line[0]);
            int rowsNumber = Integer.parseInt(line[1]);
            Main main = new Main(rowsNumber);
            for (int j = 0; j < rowsNumber; j++) {
                line = reader.readLine().split(" ");
                main.add(line[0]);
            }
            main.sort();
            printArray(main.dictionary);
        }
    }

    private static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
            if (i == array.length - 1) System.out.println("");
        }
    }

}