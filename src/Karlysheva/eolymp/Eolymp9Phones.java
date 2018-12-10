package eolymp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Eolymp9Phones {
    static BufferedReader reader;
    public static void main(String[] args) throws IOException {
       reader = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(reader.readLine());
        for (int i = 0; i < tests; i++)
            test();
    }

    static void test() throws IOException{
        int lines = Integer.parseInt(reader.readLine());
        String[] numbers = new String[lines];

        for (int i = 0; i < lines; i++) {
            numbers[i]=reader.readLine();
        }
        System.out.println(Arrays.toString(numbers));
    }
    static String compatiable (String[] numbers){
        String yes = "YES", no = "NO";
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length-1; i++) {
            if (numbers[i].length()<=numbers[i+1].length()&&numbers[i+1].substring(0, numbers[i].length()).equals(numbers[i])) return no;
        }
        return yes;
    }
    static Comparator custom = new Comparator() {
        @Override
        public int compare(Object s1, Object s2) {
            String o1 = (String) s1;
            String o2 = (String) s2;
            for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
                if (o1.charAt(i)<o2.charAt(i)) return -1;
                else if (o1.charAt(i)>o2.charAt(i))return 1;
            }
            if (o1.length()<o2.length())return -1;
            else if (o1.length()>o2.length()) return 1;
            return 0;
        }
    };
}

 /*
1
8
911
97625999
91125426
113
12340
123440
12345
98346
*/

