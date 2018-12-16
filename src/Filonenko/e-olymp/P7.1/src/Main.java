import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    Main() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(reader.ready()) {
            String[] params = reader.readLine().split(" ");
            PriorityQueue<Integer> guests = new PriorityQueue<Integer>(Collections.reverseOrder());
            for(int i=0;i<params.length;i++) {
                guests.offer(Integer.parseInt(params[i]));
            }
            System.out.println(allAreHappy(guests) ? "ok\n" : "fail\n");
        }
    }
    boolean allAreHappy(PriorityQueue<Integer> guests) {

        while(!guests.isEmpty()) {
            int guest = guests.poll();
            Stack<Integer> talkWithGuests = new Stack<Integer>();
            for(int i=0;i<guest;i++) {
                if(guests.isEmpty()) return false;
                int talkWithGuest = guests.poll();
                if(talkWithGuest != 1) {
                    talkWithGuests.push(talkWithGuest - 1);
                }
            }
            while(!talkWithGuests.isEmpty()) {
                guests.offer(talkWithGuests.pop());
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        new Main();
    }
}
