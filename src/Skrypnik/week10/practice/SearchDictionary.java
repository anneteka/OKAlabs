package week10.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SearchDictionary extends JFrame {

    private static JTextField textField;
    private static JTextArea textArea;
    private static RadixTree<String> radixTree = new RadixTree<>();

    private SearchDictionary() {
        this.setSize(800, 600);
        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.anchor = GridBagConstraints.NORTH;
        c.fill   = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth  = 2;
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        textField = new JTextField();
        gbl.setConstraints(textField,c);
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                RadixTree.resultString ="";
                radixTree.get(textField.getText().toLowerCase());
                textArea.setText(RadixTree.resultString);
            }
        };
        textField.addKeyListener(keyListener);
        c.gridheight = 6;
        c.gridy = 2;
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        gbl.setConstraints(scrollPane,c);
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.gridy = 8;
        JButton addButton = new JButton("ADD");
        gbl.setConstraints(addButton,c);
        c.gridx = 2;
        JButton deleteButton = new JButton("DELETE");
        gbl.setConstraints(deleteButton,c);
        this.add(textField);
        this.add(scrollPane);
        addButton.addActionListener(e -> addWord(textField.getText()));
        deleteButton.addActionListener(e -> delWord(textField.getText()));
        this.add(addButton);
        this.add(deleteButton);
    }

    private void addWord(String word) {
        radixTree.put(word, word);
    }

    private void delWord(String word) {
        radixTree.remove(word, word);
    }

    public static void main(String[] args) {
        SearchDictionary searchDictionary = new SearchDictionary();
        searchDictionary.setVisible(true);
        try {
            FileReader fileReader = new FileReader("src/week10/practice/file.txt");
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
}