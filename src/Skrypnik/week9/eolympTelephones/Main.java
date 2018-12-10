package week9.eolympTelephones;

import java.io.*;
import java.util.Arrays;

public class Main {

    static int tests;
    static boolean bool = true;
    static String[] telephonesArray;
    static boolean result;
    static String output = "";

    public static void main(String[] args) throws NumberFormatException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/week9/eolympTelephones/input.txt"));
            PrintWriter printWriter = new PrintWriter(new FileWriter("src/week9/eolympTelephones/output.txt"));
            String s = bufferedReader.readLine();
            tests = Integer.parseInt(s);
            if (tests < 1 || tests > 40) {
                System.out.println("Wrong format of the number!");
                return;
            }
            int numbers;
            for (int i = 0; i < tests; i++) {
                result = true;
                s = bufferedReader.readLine();
                numbers = Integer.parseInt(s);
                telephonesArray = new String[numbers];
                for (int j = 0; j < numbers; j++) {
                    if (s != null) {
                        s = bufferedReader.readLine();
                        check(s);
                        if (bool) {
                            telephonesArray[j] = s;
                        }
                    }
                }
                Arrays.sort(telephonesArray);
                for (int k = 0; k < numbers - 1; k++)
                    if (telephonesArray[k + 1].length() >= telephonesArray[k].length())
                        if (telephonesArray[k].equals(telephonesArray[k + 1].substring(0, telephonesArray[k].length())))
                            result = false;
                if (result)
                    output += "YES" + '\n';
                else output += "NO" + '\n';
            }
            printWriter.write(output);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void check(String s) {
        if (s.length() > 10) {
            System.out.println("Number is too long!");
            bool = false;
        }
        for (int k = 0; k < s.length(); k++)
            if (!Character.isDigit(s.charAt(k))) {
                throw new NumberFormatException();
            }
    }


}
