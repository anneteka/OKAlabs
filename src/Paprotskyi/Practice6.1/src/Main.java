import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static InputReader reader;
    static OutputWriter writer;

    public static void main(String[] args){
        reader = new InputReader(System.in);
        writer = new OutputWriter(System.out);
        int k = reader.nextInt();
        boolean a = false;
        int n, m;
        ArrayList<DNA> dnas = new ArrayList<>();
        while(k-->0) {
            n = reader.nextInt();
            m = reader.nextInt();
            for(int i=0;i<m;i++){
                dnas.add(new DNA(reader.nextLine(), i, n));
            }
            Collections.sort(dnas);
            //print line
            if(a)   writer.println();
            else    a = true;
            //print result
            for(DNA d : dnas) {
                writer.println(d.toString());
            }
            dnas.clear();
        }
        writer.flush();
    }
    static class DNA implements Comparable<DNA>{
        public String dna;
        public int index, sortedness;

        public DNA(String dna, int index, int length) {
            this.dna = dna;
            this.index = index;
            int a, c, g, t;
            sortedness = 0;
            a = c = g = t = 0;
            for(int i=length-1;i>=0;i--){
                switch(dna.charAt(i)){
                    case 'A':
                        a++;
                        break;
                    case 'C':
                        c++;
                        sortedness += a;
                        break;
                    case 'T':
                        t ++;
                        sortedness += a + c + g;
                        break;
                    case 'G':
                        g ++;
                        sortedness += a + c;
                        break;
                }
            }
        }

        @Override
        public int compareTo(DNA o) {
            if(sortedness != o.sortedness){
                return sortedness - o.sortedness;
            }else{
                return index - o.index;
            }
        }

        @Override
        public String toString() {
            return dna;
        }
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (Exception e) {
                    return null;
                }
            }
            return tokenizer.nextToken();
        }
        public String nextLine(){
            if(tokenizer != null && tokenizer.hasMoreTokens()){
                StringBuilder sb = new StringBuilder(tokenizer.nextToken());
                while(tokenizer.hasMoreTokens()){
                    sb.append(" ").append(tokenizer.nextToken());
                }
                return sb.toString();
            }else{
                try {
                    return reader.readLine();
                }catch(Exception e){
                    return null;
                }
            }
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public BufferedWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new BufferedWriter(new OutputStreamWriter(stream));
        }
        public void print(String string){
            try {
                writer.write(string, 0, string.length());
            }catch(Exception e){e.printStackTrace();}
        }

        public void println(String string){
            print(string);
            newline();
        }
        public void println(){
            newline();
        }

        private void newline() {
            try {
                writer.newLine();
            }catch(Exception e){e.printStackTrace();}
        }
        private void flush(){
            try {
                writer.flush();
            }catch(Exception e){e.printStackTrace();}
        }
    }
}