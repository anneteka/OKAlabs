package eolymp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eolymp8Icecream {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String data = reader.readLine();
        int n =Integer.parseInt(data.split(" ")[0]);
        int k = Integer.parseInt(data.split(" ")[1]);
        int[] coordinates = new int[n];
        String[] line = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            coordinates[i]=Integer.parseInt(line[i]);
        }
        System.out.println(findMin(coordinates, k));
    }

   static int findMin(int[] coordinates, int k){
        int n = coordinates.length;
        int[] icePoints = new int[n];
        int average = (coordinates[n-1]-coordinates[0])/n;
        icePoints[0]=1;
        icePoints[n-1]=1;
       for (int i = 0; i < n; i++) {
           
       }
        return 0;
   }
}
