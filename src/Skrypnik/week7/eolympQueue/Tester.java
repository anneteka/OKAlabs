package week7.eolympQueue;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Tester {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("src/week7/eolympQueue/input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("src/week7/eolympQueue/output.txt"));

        String s;
        StringTokenizer st;

        s = br.readLine();
        st = new StringTokenizer(s);
        long sizeOfQueue = Long.parseLong(st.nextToken());
        long numberOfWindows = Long.parseLong(st.nextToken());

        s = br.readLine();
        st = new StringTokenizer(s);
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(long i = 0; i < sizeOfQueue; i++) {
            long time = Long.parseLong(st.nextToken());
            if (pq.size() != numberOfWindows) pq.add(time);
            else pq.add(pq.poll()+time);
        }

        while(pq.size()>1) pq.poll();
        long answer = pq.poll();
        pw.print(answer);

        br.close();
        pw.close();
    }
}


