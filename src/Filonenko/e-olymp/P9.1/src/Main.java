import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static boolean compatible(String[] numbers) {
        Arrays.sort(numbers);
        for(int i=1;i<numbers.length;i++) {
            if(numbers[i].startsWith(numbers[i-1])) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTests = Integer.parseInt(reader.readLine());
        for(int i=0;i<numberOfTests;i++) {
            int numberOfPhones = Integer.parseInt(reader.readLine());
            String[] phones = new String[numberOfPhones];
            for(int j=0;j<numberOfPhones;j++) {
                phones[j] = reader.readLine();
            }
            System.out.println(compatible(phones) ? "YES" : "NO");
        }
    }
}
