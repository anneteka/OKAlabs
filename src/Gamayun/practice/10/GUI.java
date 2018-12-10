
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.imageio.ImageIO.read;

public class GUI {

    private SearchDictionary sd = new SearchDictionary();
    private JFrame frame;
    private JTextField txtWord;
    JLabel lblNewLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public GUI() throws IOException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws IOException {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 204, 153));
        frame.setResizable(false);
        frame.setBounds(100, 100, 712, 571);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);

        JLabel lblDictionary = new JLabel("Dictionary");
        springLayout.putConstraint(SpringLayout.NORTH, lblDictionary, 43, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblDictionary, 10, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, lblDictionary, 696, SpringLayout.WEST, frame.getContentPane());
        lblDictionary.setHorizontalAlignment(SwingConstants.CENTER);
        lblDictionary.setForeground(new Color(255, 255, 204));
        lblDictionary.setFont(new Font("Century Gothic", Font.PLAIN, 73));
        frame.getContentPane().add(lblDictionary);

        txtWord = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, txtWord, 154, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, lblDictionary, -34, SpringLayout.NORTH, txtWord);
        txtWord.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtWord.setText("Word...");
        springLayout.putConstraint(SpringLayout.WEST, txtWord, 77, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, txtWord, -76, SpringLayout.EAST, frame.getContentPane());
        frame.getContentPane().add(txtWord);
        txtWord.setColumns(10);

        JButton btnNewButton = new JButton("Add word");
        springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -239, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, txtWord, -27, SpringLayout.NORTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 239, SpringLayout.NORTH, frame.getContentPane());
        btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        btnNewButton.setBackground(Color.WHITE);
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 76, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 251, SpringLayout.WEST, frame.getContentPane());
        frame.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sd.addWord(txtWord.getText());

            }
        });

        JButton btnNewButton_1 = new JButton("Search");
        btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        btnNewButton_1.setBackground(Color.WHITE);
        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 6, SpringLayout.EAST, btnNewButton);
        springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 0, SpringLayout.SOUTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 181, SpringLayout.EAST, btnNewButton);
        frame.getContentPane().add(btnNewButton_1);


        JButton btnNewButton_2 = new JButton("Delete word");


        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_2, 0, SpringLayout.NORTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton_2, 6, SpringLayout.EAST, btnNewButton_1);
        springLayout.putConstraint(SpringLayout.EAST, btnNewButton_2, 0, SpringLayout.EAST, txtWord);
        btnNewButton_2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        btnNewButton_2.setBackground(Color.white);
        frame.getContentPane().add(btnNewButton_2);

        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sd.delWord(txtWord.getText());
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -18, SpringLayout.NORTH, scrollPane);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 18, SpringLayout.SOUTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, txtWord);
        springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 215, SpringLayout.SOUTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, txtWord);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -18, SpringLayout.NORTH, textPane);
        springLayout.putConstraint(SpringLayout.NORTH, textPane, 18, SpringLayout.SOUTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.WEST, textPane, 0, SpringLayout.WEST, txtWord);
        springLayout.putConstraint(SpringLayout.SOUTH, textPane, 215, SpringLayout.SOUTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.EAST, textPane, 0, SpringLayout.EAST, txtWord);
        scrollPane.getViewport().add(textPane);
        frame.getContentPane().add(scrollPane);

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = "";
                for(String s: sd.query(txtWord.getText()))
                    res +=s +"\r\n";
                textPane.setText(res);
            }
        });

        JButton btnOpenFile = new JButton("Choose file");
        btnOpenFile.setBackground(Color.WHITE);
        springLayout.putConstraint(SpringLayout.WEST, btnOpenFile, 10, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnOpenFile, -6, SpringLayout.NORTH, lblDictionary);
        frame.getContentPane().add(btnOpenFile);
        btnOpenFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    openFile();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });


         lblNewLabel = new JLabel("New label");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, btnOpenFile);
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, -156, SpringLayout.EAST, lblDictionary);
        springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6, SpringLayout.NORTH, lblDictionary);
        springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -10, SpringLayout.EAST, frame.getContentPane());
        frame.getContentPane().add(lblNewLabel);
    }
    private void read(File file)throws IOException {
        FileReader fr = new FileReader(file);
        StreamTokenizer st = new StreamTokenizer(fr);
        while(st.nextToken()!=StreamTokenizer.TT_EOF){
            if(st.sval!=null)
                sd.addWord(st.sval);
        }
        lblNewLabel.setText("Num of words: " + sd.countWords());
        lblNewLabel.repaint();
    }

    private void openFile() throws IOException {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
//      System.out.println(selectedFile.getAbsolutePath());
            read(selectedFile);
        }
    }
}