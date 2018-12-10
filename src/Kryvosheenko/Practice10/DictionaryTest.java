
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DictionaryTest {

	private static SearchDictionary dictionary;
	private static JFrame frame;
	// sizes of frame
	public final static int HEIGHT = 580;
	public final static int WIDTH = 600;
	private static JTextField searchField;
	private static JPanel resultPanel;
	private static JLabel restotalL;
	private static JTable table;
	private static JScrollPane scroll;

	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("text.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		dictionary = new SearchDictionary();
		fillDictionary(reader);
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void fillDictionary(BufferedReader reader) {
		StringTokenizer tk;
		while (true) {
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (line == null)
				break;
			tk = new StringTokenizer(line);
			while (tk.hasMoreTokens()) {
				dictionary.addWord(tk.nextToken());
			}
		}
		initialize();
	}

	/**
	 * initialize all components
	 */

	private static void initialize() {
		setUIFont(new FontUIResource(new Font("Arial", 0, 20)));
		frame = new JFrame("Dictionary");
		JPanel defaultPanel = new JPanel();

		JLabel totalL = new JLabel("Total: ");
		defaultPanel.add(totalL);
		restotalL = new JLabel(Integer.toString(dictionary.countWords()));
		defaultPanel.add(restotalL);
		searchField = new JTextField(20);
		searchField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					refreshResultPanel();
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		defaultPanel.add(searchField);

		JButton addButton = new JButton("+");
		addButton.setPreferredSize(new Dimension(50, 25));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createAddingFrame();
			}
		});
		defaultPanel.add(addButton);
		resultPanel = new JPanel();
		JLabel firstL = new JLabel("Input word in a filed and press enter.");
		resultPanel.add(firstL, BorderLayout.SOUTH);

		frame.getContentPane().add(resultPanel, BorderLayout.CENTER);
		frame.getContentPane().add(defaultPanel, BorderLayout.NORTH);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
	}

	protected static void createAddingFrame() {
		JFrame addFrame = new JFrame("Adding new word");
		JPanel panel = new JPanel(new BorderLayout());
		JLabel text = new JLabel("Enter new word in the field");
		panel.add(text, BorderLayout.NORTH);
		JTextField addingField = new JTextField(20);
		panel.add(addingField, BorderLayout.CENTER);
		JButton confirmAddingButton = new JButton("Add");
		confirmAddingButton.setSize(new Dimension(80, 20));
		confirmAddingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newWord = addingField.getText();
				dictionary.addWord(newWord);
				restotalL.setText(Integer.toString(dictionary.countWords()));
				addFrame.setVisible(false);
				addFrame.dispose();
				refreshResultPanel();

			}
		});
		panel.add(confirmAddingButton, BorderLayout.SOUTH);
		addFrame.getContentPane().add(panel);

		addFrame.setVisible(true);
		addFrame.setResizable(false);
		addFrame.setSize(300, 200);
	}

	protected static void refreshResultPanel() {
		String word = searchField.getText();
		if (word != "") {

			ArrayList<String> list = (ArrayList) dictionary.query(word);
			resultPanel.removeAll();
			if (list != null) {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Found words", list.toArray());
				table = new JTable(model);
				table.setRowHeight(25);

				scroll = new JScrollPane(table);
				scroll.setVisible(true);
				// delete button
				JButton deleteButton = new JButton("Delete");
				deleteButton.setPreferredSize(new Dimension(150, 30));
				deleteButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int confirmed = JOptionPane.showConfirmDialog(null, "Delete all selected words?", "Delete",
								JOptionPane.YES_NO_OPTION);
						if (confirmed == JOptionPane.NO_OPTION) {
							return;
						}
						int selectedRows[] = table.getSelectedRows();
						String[] words = new String[selectedRows.length];
						for (int i = 0; i < selectedRows.length; i++) {
							words[i] = (String) table.getValueAt(selectedRows[i], 0);
						}
						for (String delWords : words) {
							dictionary.delWord(delWords);
						}
						refreshResultPanel();
					}
				});

				resultPanel.add(scroll, BorderLayout.NORTH);
				resultPanel.add(deleteButton, BorderLayout.SOUTH);
			} else {
				JLabel noWordsLabel = new JLabel("No words are found.");
				resultPanel.add(noWordsLabel, BorderLayout.NORTH);
			}

			resultPanel.revalidate();
			resultPanel.repaint();

		}
	}

	private static void refreshTable() {
		// TODO Auto-generated method stub

	}

	public static void setUIFont(FontUIResource f) {
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				FontUIResource orig = (FontUIResource) value;
				Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}
}
