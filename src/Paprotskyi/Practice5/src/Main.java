import edu.princeton.cs.algs4.*;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(new File("input.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = Integer.parseInt(br.readLine());
        Worker[] w = new Worker[n];

        for (int i=0; i<n; i++){
            String s = br.readLine();
            String[] m = s.split(" ");
            w[i] = new Worker(Integer.parseInt(m[0]),Integer.parseInt(m[1]),Integer.parseInt(m[2]));
        }

        System.out.println("- - - Input - - -");
        for(Worker a: w)
            System.out.println(a);

        Insertion.sort(w,new By_Age());

        //Selection.sort(w,new By_Experience());

        // doesn't work with comparator //Shell.sort(w,new By_Experience());
        //doesn't work with comparator Merge.sort(w, new By_Grade());
        //doesn't work with comparator Quick.sort(w, new By_Grade());


        //Insertion.sort(w,new By_Grade());
        //Insertion.sort(w,new By_Experience());
        //TODO інші сортування з бібліотеки, якщо що спитати в старости

        System.out.println("- - - Result - - -");
        for(Worker a: w)
            System.out.println(a);

        br.close();
    }
}
