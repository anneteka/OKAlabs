import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SearchDictionary{

    private static JTextField textField;
    private static JButton add;
    private static JButton delete;
    private static JButton allWords;
    private static JTextArea textArea;
    private static RadixTree<String> radixTree = new RadixTree<>();
    private static PrintWriter printWriter;


    static void readFile() {
        try {
            FileReader fileReader = new FileReader("file.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = bufferedReader.readLine();
            String token;
            while (s != null) {
                StringTokenizer tokenizer = new StringTokenizer(s, " ,.!?:-;");
                while (tokenizer.hasMoreTokens()) {
                    token = tokenizer.nextToken();
                    token = token.toLowerCase();
                    radixTree.put(token, token);
                }
                s = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String wordInput="";
        readFile();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Оберіть дію:\n" +
                    "1.Додати слово\n" +
                    "2.Видалити слово\n" +
                    "3.Показати всі слова\n");
            int choice = sc.nextInt();

                switch (choice) {
                    case (1):
                        System.out.println("Введіть слово: ");
                        while(true){
                            wordInput = sc.nextLine();
                            if(!wordInput.isEmpty()){
                                break;
                            }
                        }
                        radixTree.put(wordInput, wordInput);
                        try {
                            printWriter = new PrintWriter(new FileWriter("file.txt", true));
                            printWriter.println(wordInput);
                            printWriter.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        radixTree.resultString = "";
                        radixTree.get(wordInput.toLowerCase());
                        System.out.println(radixTree.resultString);
                            break;


                    case (2):
                        System.out.println("Введіть слово: ");
                        while(true){
                            wordInput = sc.nextLine();
                            if(!wordInput.isEmpty()){
                                break;
                            }
                        }
                            radixTree.remove(wordInput, wordInput);
                            radixTree.resultString = "";
                            radixTree.get(wordInput.toLowerCase());
                            System.out.println(radixTree.resultString);

                    case (3):
                        radixTree.resultString = "";
                        radixTree.get("");
                        System.out.println(radixTree.resultString);
                        break;
                }

        }
    }
}