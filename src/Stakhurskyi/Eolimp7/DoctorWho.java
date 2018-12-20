package Stakhurskyi.Eolimp7;

import java.io.*;
import java.util.*;

public class DoctorWho {
    public static void main(String[] args) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("input.txt"));
        } catch (FileNotFoundException e) {

        }
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        StringTokenizer srtT;
        ArrayList<Short> arr;

        String line = in.readLine();
        while (true) {

            if(!line.matches("[0-9]+")){
                prntAnswer("wrong input", pw);
                line = null;
            }
            if (line == null)
            {break;}


            arr = new ArrayList<>();
            srtT = new StringTokenizer(line);

            while (srtT.hasMoreTokens()) {
                short el = Short.parseShort(srtT.nextToken());
                arr.add(el);
            }
            Short[] guests = arr.toArray(new Short[arr.size()]);

            // If first element is bigger than array's length - fail
            if (guests[0] > guests.length - 1) {
                prntAnswer("fail", pw);
                line = in.readLine();
            } else {
                for (short n = 0; n < guests.length; n++) {
                    if (n != 0)
                        Arrays.sort(guests, Collections.reverseOrder());
                    if (guests[0] == 0) {
                        prntAnswer("ok", pw);
                        line = in.readLine();
                        break;
                    }
                    // communicate
                    for (short i = 1; i < guests.length; i++) {
                        if (guests[i] != 0) {
                            guests[0]--;
                            guests[i]--;
                        }
                        if (guests[0] == 0) {
                            break;
                        }
                    }
                    if (guests[0] != 0) {
                        prntAnswer("fail", pw);
                        line = in.readLine();
                        break;
                    }

                }
            }
        }
        pw.flush();
    }

    private static void prntAnswer(String s, PrintWriter pw) {
        pw.println(s);
        pw.println();
    }
}