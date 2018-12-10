import java.util.Scanner;

public class IceCream {
	
    public static void main(String args[]){
    	
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int k=scan.nextInt();
        int []m=new int[n];
        for(int i = 0; i < n; i++)
            m[i]=scan.nextInt();
        
        int Min = 0;
        int Max = 1000000000;
        
        IceCream iceCream=new IceCream();

        while(Min <= Max)
        {
            int middle = (Min + Max) / 2;
            
            if (iceCream.func(middle,m,k))
            	Min = middle + 1; 
            else Max = middle - 1;
            
        }
        
        System.out.println(Min-1);
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