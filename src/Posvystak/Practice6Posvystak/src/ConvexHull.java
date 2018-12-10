import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ConvexHull {

    static int size;
    static Point2D[] array;
    static ArrayStack<Point2D> points = new ArrayStack<Point2D>();

    static void readFile() {
        try {
            FileReader fileReader = new FileReader("/Users/ansttss/Downloads/Practice6_ConvexHull 2/pr4_5_data/input56.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = bufferedReader.readLine();

            if (s != null) {
                for (int i = 0; i < s.length(); i++) {
                    if (!Character.isDigit(s.charAt(i))) {
                        System.out.println("Wrong format of the number!");
                        return;
                    }
                }
                size = Integer.parseInt(s);
            }

            array = new Point2D[size];
            s = bufferedReader.readLine();
            String token;
            int number1;
            int number2;
            int counter = 0;

            while (s != null && counter < size) {
                StringTokenizer tokenizer = new StringTokenizer(s);
                if (tokenizer.hasMoreTokens()) {
                    token = tokenizer.nextToken();
                    for (int i = 0; i < token.length(); i++) {
                        if (!Character.isDigit(token.charAt(i))) {
                            System.out.println("Wrong format of the number!");
                            return;
                        }
                    }
                    number1 = Integer.parseInt(token);

                    if (tokenizer.hasMoreTokens()) {
                        token = tokenizer.nextToken();
                        for (int i = 0; i < token.length(); i++) {
                            if (!Character.isDigit(token.charAt(i))) {
                                System.out.println("Wrong format of the number!");
                                return;
                            }
                        }
                        number2 = Integer.parseInt(token);
                        array[counter] = (new Point2D(number1, number2));
                        array[counter].draw();
                        counter++;
                        s = bufferedReader.readLine();

                    } else System.out.println("Not enough numbers");
                } else System.out.println("Not enough numbers");
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        readFile();
        Arrays.sort(array);
        MergeSort.sort(array, array[0].POLAR_ORDER);
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
            array[i].draw();
        }

        points.push(array[0]);
        int counter;
        for (counter = 2; counter < size; counter++)
            if (Point2D.ccw(array[0], array[1], array[counter]) != 0) break;
        points.push(array[counter - 1]);
        for (int i = counter; i < size; i++) {
            Point2D top = points.pop();
            while (Point2D.ccw(points.peek(), top, array[i]) >= 0) {
                top = points.pop();
            }
            points.push(top);
            points.push(array[i]);
        }

        ArrayList<Point2D> forDraw = new ArrayList<>();
        for (Point2D point: points)
            forDraw.add(point);

        String output="";
        for (int i = 0;i<forDraw.size()-1;i++){
            output+=forDraw.get(i).toString()+" --> ";
            forDraw.get(i).drawTo(forDraw.get(i+1));
        }
        output+=forDraw.get(forDraw.size()-1).toString()+" --> "+forDraw.get(0).toString();
        forDraw.get(forDraw.size()-1).drawTo(forDraw.get(0));
        System.out.println(output);
    }

}
