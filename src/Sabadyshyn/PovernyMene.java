import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class PovernyMene
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] res = new String[br.read()+1];
        for(int i = 1; i < res.length; i++)
        {res[i] = "";}

        br.readLine();
        for(int i = 1; i < res.length; i++)
        {
            for(String str : br.readLine().split(" "))
            {
                if(str.equals(""))
                {break;}
                res[Integer.parseInt(str)] += i + " ";
            }
        }

        br.close();

        System.out.println(res.length - 1);
        for(int i = 1; i < res.length; i++)
        {System.out.println(res[i]);}
    }
}