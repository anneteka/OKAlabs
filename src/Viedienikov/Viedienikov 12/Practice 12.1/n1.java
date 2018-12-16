import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n1 {
    public static void main(String[]args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int V=Integer.parseInt(in.readLine());
        String []arr=new String[V];
        for (int i = 0; i < V; i++) {
            String[]x=(in.readLine()).split(" ");
            for (int j = 0; j < x.length; j++) {
                if(x[j].equals("")) {break;}
                if(arr[Integer.parseInt(x[j])-1]==null)arr[Integer.parseInt(x[j])-1]="";
                arr[Integer.parseInt(x[j])-1]+=(i+1)+" ";
            }
        }
        System.out.println(V);

        for (int i = 0; i < V; i++) {
            if(arr[i]==null) System.out.println("");
            else System.out.println(arr[i]);
        }
    }
}
