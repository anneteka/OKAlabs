import java.util.*;
import java.io.*;

public class Main
{
  public static void main(String[] args)  throws IOException
  {
 Scanner myread= new Scanner(System.in);
    int n = myread.nextInt();
    int masnn[][] = new int[n][n];
    for(int i = 0; i < n; i++)
       for(int j = 0; j < n; j++){
            masnn[i][j] = myread.nextInt();
            if ((i == j) && (masnn[i][j] == 1)){
             System.out.println("NO");
             return;
       }
   }
  for(int i = 0; i < n; i++)
  for(int j = 0; j < n; j++)
  if (masnn[i][j] != masnn[j][i]){
  System.out.println("NO");
  return;
}
    System.out.println("YES");
    myread.close();
  }
}