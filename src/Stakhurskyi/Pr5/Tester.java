package Stakhurskyi.Pr5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


 class Test {

    static final int n = 10;

    public static void main(String[] args) throws Exception {
        String thisLine = null;
        StringTokenizer st;
        Product[] p = new Product[n];

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dmytro\\Desktop\\workspace5\\Practice5\\src\\Test.txt"));

         /*while ((thisLine = br.readLine()) != null)*/


        for(int i=0;i<n;i++) {
            thisLine = br.readLine();
            st = new StringTokenizer(thisLine, " ");
            p[i] = new Product(st.nextToken(),Double.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()));
        }


        System.out.println("no sorted ");

        for(Product i : p) {
            System.out.println("Name: " + i.getName() +
                    " price: " + i.getPrice() +
                    " quantity: " + i.getQuantity());
        }


        System.out.println("sorted by price");

        Arrays.sort(p, new SortedByPrice());

        for(Product i : p) {
            System.out.println("Name: " + i.getName() +
                    " price: " + i.getPrice() +
                    " quantity: " + i.getQuantity());
        }


        System.out.println("sorted by name");

        Arrays.sort(p, new SortedByName());
        for(Product i : p) {
            System.out.println("Name: " + i.getName() +
                    " price: " + i.getPrice() +
                    " quantity: " + i.getQuantity());
        }

        System.out.println("sorted by Quantity(Comparable)");

        Arrays.sort(p);
        for(Product i : p) {
            System.out.println("Name: " + i.getName() +
                    " price: " + i.getPrice() +
                    " quantity: " + i.getQuantity());
        }



    }
}