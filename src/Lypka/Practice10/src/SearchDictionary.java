import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.StringTokenizer;

public class SearchDictionary extends JFrame {

	private static JTextField textField;
	private static JButton add;
	private static JButton delete;
	private static JButton allWords;
	private static JTextArea textArea;
	private static RadixTree<String> radixTree = new RadixTree<>();
	private static PrintWriter printWriter;

	public SearchDictionary() {
		this.setSize(800, 600);
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		textField = new JTextField();
		gbl.setConstraints(textField, c);
		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				radixTree.resultString = "";
				radixTree.get(textField.getText().toLowerCase());
				if (textField.getText().toLowerCase().equals(""))
					textArea.setText("");
				else
					textArea.setText(radixTree.resultString);
			}
		};
		textField.addKeyListener(keyListener);

		c.gridheight = 6;
		c.gridy = 2;
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		gbl.setConstraints(scrollPane, c);

		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridy = 8;
		add = new JButton("ADD (type full word)");
		gbl.setConstraints(add, c);

		c.gridx = 2;
		delete = new JButton("DELETE (type full word)");
		gbl.setConstraints(delete, c);

		c.gridx = 3;
		allWords = new JButton("VIEW ALL");
		gbl.setConstraints(allWords, c);

		this.add(textField);
		this.add(scrollPane);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = textField.getText();
				addWord(word);
				try {
					printWriter = new PrintWriter(new FileWriter("file.txt", true));
					printWriter.println(word);
					printWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				radixTree.resultString = "";
				radixTree.get(textField.getText().toLowerCase());
				textArea.setText(radixTree.resultString);
			}
		});
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delWord(textField.getText());
				radixTree.resultString = "";
				radixTree.get(textField.getText().toLowerCase());
				textArea.setText(radixTree.resultString);
			}
		});
		allWords.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radixTree.resultString = "";
				radixTree.get("");
				textArea.setText(radixTree.resultString);
			}
		});
		this.add(add);
		this.add(delete);
		this.add(allWords);
	}

	public void addWord(String word) {
		radixTree.put(word, word);
	}

	public void delWord(String word) {
		radixTree.remove(word, word);
	}

	public boolean hasWord(String word) {
		return radixTree.containsValue(word);
	}

	public Iterable<String> query(String query) {
		return null;
	}

	public int countWords() {
		return radixTree.size();
	}

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
		SearchDictionary searchDictionary = new SearchDictionary();
		searchDictionary.setVisible(true);
		readFile();
	}
}