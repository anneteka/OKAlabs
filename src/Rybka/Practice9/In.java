
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class In {
    
    private Scanner scanner;
    
    private static final String charsetName = "UTF-8";

    private static final java.util.Locale usLocale = 
        new java.util.Locale("en", "US");

    private static final Pattern WHITESPACE_PATTERN
        = Pattern.compile("\\p{javaWhitespace}+");

    private static final Pattern EMPTY_PATTERN
        = Pattern.compile("");

    private static final Pattern EVERYTHING_PATTERN
        = Pattern.compile("\\A");

    public In() {
        scanner = new Scanner(new BufferedInputStream(System.in), charsetName);
        scanner.useLocale(usLocale);
    }
    
    public In(java.net.Socket socket) {
        try {
            InputStream is = socket.getInputStream();
            scanner = new Scanner(new BufferedInputStream(is), charsetName);
            scanner.useLocale(usLocale);
        }
        catch (IOException ioe) {
            System.err.println("Could not open " + socket);
        }
    }

    public In(URL url) {
        try {
            URLConnection site = url.openConnection();
            InputStream is     = site.getInputStream();
            scanner            = new Scanner(new BufferedInputStream(is), charsetName);
            scanner.useLocale(usLocale);
        }
        catch (IOException ioe) {
            System.err.println("Could not open " + url);
        }
    }

    public In(File file) {
        try {
            scanner = new Scanner(file, charsetName);
            scanner.useLocale(usLocale);
        }
        catch (IOException ioe) {
            System.err.println("Could not open " + file);
        }
    }


    public In(String s) {
        try {
            File file = new File(s);
            if (file.exists()) {
                scanner = new Scanner(file, charsetName);
                scanner.useLocale(usLocale);
                return;
            }

            URL url = getClass().getResource(s);

            if (url == null) { url = new URL(s); }

            URLConnection site = url.openConnection();
            InputStream is     = site.getInputStream();
            scanner            = new Scanner(new BufferedInputStream(is), charsetName);
            scanner.useLocale(usLocale);
        }
        catch (IOException ioe) {
            System.err.println("Could not open " + s);
        }
    }

    public In(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean exists()  {
        return scanner != null;
    }
    
    public boolean isEmpty() {
        return !scanner.hasNext();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }


    public String readLine() {
        String line;
        try                 { line = scanner.nextLine(); }
        catch (Exception e) { line = null;               }
        return line;
    }
    
    public char readChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        String ch = scanner.next();
        assert (ch.length() == 1) : "Internal (Std)In.readChar() error!"
            + " Please contact the authors.";
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return ch.charAt(0);
    }  


    public String readAll() {
        if (!scanner.hasNextLine())
            return "";

        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        // not that important to reset delimeter, since now scanner is empty
        scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
        return result;
    }


    public String readString() {
        return scanner.next();
    }

    public int readInt() {
        return scanner.nextInt();
    }

    public double readDouble() {
        return scanner.nextDouble();
    }

    public float readFloat() {
        return scanner.nextFloat();
    }

    public long readLong() {
        return scanner.nextLong();
    }

    public short readShort() {
        return scanner.nextShort();
    }

    public byte readByte() {
        return scanner.nextByte();
    }

    public boolean readBoolean() {
        String s = readString();
        if (s.equalsIgnoreCase("true"))  return true;
        if (s.equalsIgnoreCase("false")) return false;
        if (s.equals("1"))               return true;
        if (s.equals("0"))               return false;
        throw new java.util.InputMismatchException();
    }

    public String[] readAllStrings() {
        // we could use readAll.trim().split(), but that's not consistent
        // since trim() uses characters 0x00..0x20 as whitespace
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0)
            return tokens;
        String[] decapitokens = new String[tokens.length-1];
        for (int i = 0; i < tokens.length-1; i++)
            decapitokens[i] = tokens[i+1];
        return decapitokens;
    }
    
    public int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Integer.parseInt(fields[i]);
        return vals;
    }

    public double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Double.parseDouble(fields[i]);
        return vals;
    }
    
    public void close() {
        scanner.close();  
    }

    public static int[] readInts(String filename) {
        return new In(filename).readAllInts();
    }

    public static double[] readDoubles(String filename) {
        return new In(filename).readAllDoubles();
    }

    public static String[] readStrings(String filename) {
        return new In(filename).readAllStrings();
    }

    public static int[] readInts() {
        return new In().readAllInts();
    }


    public static double[] readDoubles() {
        return new In().readAllDoubles();
    }

    public static String[] readStrings() {
        return new In().readAllStrings();
    }
    
    public static void main(String[] args) {
        In in;
        String urlName = "http://introcs.cs.princeton.edu/stdlib/InTest.txt";

        // read from a URL
        System.out.println("readAll() from URL " + urlName);
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            System.out.println(in.readAll());
        }
        catch (Exception e) { System.out.println(e); }
        System.out.println();

        // read one line at a time from URL
        System.out.println("readLine() from URL " + urlName);
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            while (!in.isEmpty()) {
                String s = in.readLine();
                System.out.println(s);
            }
        }
        catch (Exception e) { System.out.println(e); }
        System.out.println();

        // read one string at a time from URL
        System.out.println("readString() from URL " + urlName);
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            while (!in.isEmpty()) {
                String s = in.readString();
                System.out.println(s);
            }
        }
        catch (Exception e) { System.out.println(e); }
        System.out.println();


        // read one line at a time from file in current directory
        System.out.println("readLine() from current directory");
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In("./InTest.txt");
            while (!in.isEmpty()) {
                String s = in.readLine();
                System.out.println(s);
            }
        }
        catch (Exception e) { System.out.println(e); }
        System.out.println();


        // read one line at a time from file using relative path
        System.out.println("readLine() from relative path");
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In("../stdlib/InTest.txt");
            while (!in.isEmpty()) {
                String s = in.readLine();
                System.out.println(s);
            }
        }
        catch (Exception e) { System.out.println(e); }
        System.out.println();

        // read one char at a time
        System.out.println("readChar() from file");
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In("InTest.txt");
            while (!in.isEmpty()) {
                char c = in.readChar();
                System.out.print(c);
            }
        }
        catch (Exception e) { System.out.println(e); }
        System.out.println();
        System.out.println();

        // read one line at a time from absolute OS X / Linux path
        System.out.println("readLine() from absolute OS X / Linux path");
        System.out.println("---------------------------------------------------------------------------");
        in = new In("/n/fs/introcs/www/java/stdlib/InTest.txt");
        try {
            while (!in.isEmpty()) {
                String s = in.readLine();
                System.out.println(s);
            }
        }
        catch (Exception e) { System.out.println(e); }
        System.out.println();


        // read one line at a time from absolute Windows path
        System.out.println("readLine() from absolute Windows path");
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In("G:\\www\\introcs\\stdlib\\InTest.txt");
            while (!in.isEmpty()) {
                String s = in.readLine();
                System.out.println(s);
            }
            System.out.println();
        }
        catch (Exception e) { System.out.println(e); }
        System.out.println();

    }

}
