import java.util.Scanner;

public class n2 {
    public static void main(String args[]){
        n2 n2=new n2();
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int k=scan.nextInt();
        int []m=new int[n];
        for(int i = 0; i < n; i++)
            m[i]=scan.nextInt();
        int Left = 0;
        int Right = 1000000000;
        while(Left <= Right)
        {
            int Middle = (Left + Right) / 2;
            if (n2.func(Middle,m,k)) Left = Middle + 1; else Right = Middle - 1;
        }
        System.out.println(Left-1);


    }
    boolean func(int v,int[]m,int k)
    {
        int i;
        int stall = 1;
        int len = 0;
        for(i = 1; i < m.length; i++)
        {
            len += m[i] - m[i-1];
            if (len >= v) {
                len = 0;
                stall++;
            }
        }
        return stall >= k;
    }

}
