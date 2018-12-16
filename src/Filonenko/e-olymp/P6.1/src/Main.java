import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public Main() {

    }
    private void printResult(String[][] all) {
        for(String[] test:all) {
            sortArray(test);
            for(int i=0;i<test.length;i++) {
                System.out.println(test[i]);
            }
            System.out.print("\n");
        }
    }
    private void sortArray(String[] words) {
        Arrays.sort(words, new InversionsComparator());
    }
    private class InversionsComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if(inversionsIn(o1) < inversionsIn(o2)) {
                return -1;
            }
            if(inversionsIn(o1) > inversionsIn(o2)) {
                return 1;
            }
            return 0;
        }
    }

    private int inversionsIn(String string) {
        char[] array = string.toCharArray();
        int n = array.length;
        int inv_count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j]) {
                    inv_count++;
                }
            }
        }
        return inv_count;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTests = scanner.nextInt();
        String[][] all = new String[numberOfTests][];
        for(int i=0;i<numberOfTests;i++) {
            scanner.nextLine();
            int wordLength = scanner.nextInt();
            int numberOfWords = scanner.nextInt();
            scanner.nextLine();
            String[] words = new String[numberOfWords];
            for(int j=0;j<numberOfWords;j++) {
                words[j] = scanner.nextLine();
            }
            all[i] = words;
        }
        new Main().printResult(all);
    }

}
