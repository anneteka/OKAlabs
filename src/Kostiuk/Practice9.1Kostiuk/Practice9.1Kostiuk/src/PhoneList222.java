import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PhoneList222 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numTestCases = Integer.parseInt(reader.readLine());

        while (numTestCases-- > 0) {
            int numNumbers = Integer.parseInt(reader.readLine());

            String[] phoneNumbers = new String[numNumbers];
            for (int i = 0; i < numNumbers; i++) {
                phoneNumbers[i] = reader.readLine();
            }

            Arrays.sort(phoneNumbers);
            boolean consistent = true;

            for (int i = 0; i < numNumbers - 1; i++) {
                if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                    consistent = false;
                    break;
                }
            }

            if (consistent) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

}