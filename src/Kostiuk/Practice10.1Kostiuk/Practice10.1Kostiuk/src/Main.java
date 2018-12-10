import java.math.BigInteger;
import java.util.Scanner;
public class Main {public static void main(String[] args) {
   Scanner sc = new Scanner(System.in);
   do {
   int n = sc.nextInt();
   BigInteger el1 = sc.nextBigInteger();
   BigInteger el2 = sc.nextBigInteger();
   while (!el1.equals(el2)) {
    if (el1.compareTo(el2)==1)  el1=el1.divide(BigInteger.valueOf(2)); 
    else  el2=el2.divide(BigInteger.valueOf(2)); ;
   }
   System.out.println(el1);
   }while(sc.hasNextInt());
   sc.close();
   }
 }