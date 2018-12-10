import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays; 
public class IceCream { 
 private static int[] booth; 
 private static int k; 
 
 public static void main(String[] args) throws IOException { 
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
  int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
  k = arr[1]; 
  booth = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
  int left = 0; 
  int right = 1000000000; 
  while (left <= right) { 
   int middle = (left + right) / 2; 
   if (check(middle)) 
    left = middle + 1; 
   else 
    right = middle - 1; 
  } 
  System.out.println(left - 1); 
 } 
 
 private static boolean check(int value) { 
  int s = 1; 
  int length = 0; 
  for (int i = 1; i < booth.length; i++) { 
   length += booth[i] - booth[i - 1]; 
   if (length >= value) { 
    length = 0; 
    s++; 
   } 
  } 
  boolean b = false; 
  if (s >= k) { 
   b = true; 
  } 
  return b; 
 } 
}