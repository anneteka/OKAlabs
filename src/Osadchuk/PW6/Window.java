package PW6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame
{
	private OptionPanel optionPanel;
	private ActionArea actionPanel;
	private static JLabel lines;
	
	public Window()
	{
		super("Tochechki");
		super.setBounds(10, 50, 1304, 638);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);
		super.setLayout(new GridBagLayout());
		
		doingLayout();
	}
	
	
	private void doingLayout()
	{
		GridBagConstraints mainConstraints = new GridBagConstraints();
		
		mainConstraints.weightx = 0;
		mainConstraints.weighty = 1;
		mainConstraints.fill = GridBagConstraints.BOTH;
		
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 0;
		
		optionPanel = new OptionPanel();
		super.add(optionPanel, mainConstraints);
		
		mainConstraints.weightx = 1;
		mainConstraints.gridheight = 2;
		mainConstraints.gridx = 1;
		
		actionPanel = new ActionArea(1000, 600);
		super.add(actionPanel, mainConstraints);
	}
	
	
	class OptionPanel extends JPanel
	{
		private DefaultListModel model;
		private JList allPoints;
		
		public OptionPanel()
		{
			super.setMinimumSize(new Dimension());
			super.setBackground(new Color(139,69,19));
			super.setLayout(new GridBagLayout());
			
			if(true)
				doingLayout();
		}
		
		
		private void doingLayout()
		{
			GridBagConstraints optionsPanelConstraints = new GridBagConstraints();
			
			optionsPanelConstraints.gridwidth = 4;
			optionsPanelConstraints.weighty = 1;
			optionsPanelConstraints.fill = GridBagConstraints.BOTH;
			optionsPanelConstraints.gridx = 0;
			
			optionsPanelConstraints.gridy = 0;
			optionsPanelConstraints.insets = new Insets(0, 35, 0, 0);
			
			JLabel name = new JLabel("Tochechki");
			name.setFont(new Font("TimesRoman", Font.TYPE1_FONT, 30));
			name.setForeground(new Color(142,252,0));
			super.add(name, optionsPanelConstraints);
			
			
			optionsPanelConstraints.gridy = 1;
			optionsPanelConstraints.gridx = 0;
			optionsPanelConstraints.insets = new Insets(0, 5, 0, 0);
			
			JLabel blockQuality = new JLabel("Add point:");
			blockQuality.setFont(new Font("TimesRoman", Font.TYPE1_FONT, 20));
			blockQuality.setForeground(new Color(240,255,240));
			super.add(blockQuality, optionsPanelConstraints);
			
			
			optionsPanelConstraints.gridwidth = 1;
			optionsPanelConstraints.gridy = 2;
			optionsPanelConstraints.gridx = 0;
			optionsPanelConstraints.insets = new Insets(-20, 25, 0, 10);
			
			JLabel lx = new JLabel("x:");
			lx.setFont(new Font("TimesRoman", Font.TYPE1_FONT, 15));
			lx.setForeground(new Color(240,255,240));
			super.add(lx, optionsPanelConstraints);
			
			optionsPanelConstraints.gridx = 1;
			optionsPanelConstraints.insets = new Insets(-20, 0, 0, 15);
			optionsPanelConstraints.fill   = GridBagConstraints.NONE; 
			
			final JTextField tx = new JTextField(5);
			tx.setBorder(BorderFactory.createLineBorder(Color.black));
			tx.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tx.setBorder(BorderFactory.createLineBorder(Color.black));
			    }
			});
			super.add(tx, optionsPanelConstraints);
			
			
			optionsPanelConstraints.gridx = 2;
			optionsPanelConstraints.insets = new Insets(-20, 0, 0, 0);
			optionsPanelConstraints.fill   = GridBagConstraints.BOTH;
			
			JLabel ly = new JLabel(",   y:");
			ly.setFont(new Font("TimesRoman", Font.TYPE1_FONT, 15));
			ly.setForeground(new Color(240,255,240));
			super.add(ly, optionsPanelConstraints);
			
			optionsPanelConstraints.gridx = 3;
			optionsPanelConstraints.insets = new Insets(-20, -100, 0, 0);
			optionsPanelConstraints.fill   = GridBagConstraints.NONE;
			
			final JTextField ty = new JTextField(5);
			ty.setBorder(BorderFactory.createLineBorder(Color.black));
			ty.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					ty.setBorder(BorderFactory.createLineBorder(Color.black));
			    }
			});
			super.add(ty, optionsPanelConstraints);
			
			optionsPanelConstraints.ipady = 0;
			optionsPanelConstraints.gridwidth = 1;
			optionsPanelConstraints.gridx = 2;
			optionsPanelConstraints.gridy = 3;
			optionsPanelConstraints.insets = new Insets(0, 0, 0, 0);
			optionsPanelConstraints.fill   = GridBagConstraints.NONE;
			
			JButton butAdd = new JButton("add");
			butAdd.setFont(new Font("TimesRoman", Font.ITALIC, 20));
			butAdd.setBorderPainted(false);
			butAdd.setFocusable(false);
			butAdd.setBackground(new Color(0,206,209));
			butAdd.setForeground(new Color(240,255,240));
			butAdd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					boolean isDigitX = false, isDigitY = false;
					int x = 0, y = 0;
					
					try {
						x = Integer.parseInt(tx.getText());
						isDigitX = true;
						tx.setBorder(BorderFactory.createLineBorder(Color.black));
					} catch (NumberFormatException e) {
						tx.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				    }
					
					try {
						y = Integer.parseInt(ty.getText());
						isDigitY = true;
						ty.setBorder(BorderFactory.createLineBorder(Color.black));
					} catch (NumberFormatException e) {
						ty.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				    }
					
					if(isDigitX & isDigitY){
						Poiint p = new Poiint(x, y);
						model.addElement(p.toString());
						actionPanel.addPoint(p);
					}
				}
			});
			super.add(butAdd, optionsPanelConstraints);
			
			optionsPanelConstraints.gridx = 3;
			optionsPanelConstraints.insets = new Insets(0, 0, 0, 35);
			
			JButton butRem = new JButton("rem");
			butRem.setFont(new Font("TimesRoman", Font.ITALIC, 20));
			butRem.setBorderPainted(false);
			butRem.setFocusable(false);
			butRem.setBackground(new Color(220,20,60));
			butRem.setForeground(new Color(240,255,240));
			butRem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					actionPanel.remPoint(allPoints.getSelectedIndex());
					model.removeElement(allPoints.getSelectedValue());
				}
			});
			super.add(butRem, optionsPanelConstraints);
			
			optionsPanelConstraints.gridwidth = 2;
			optionsPanelConstraints.gridx = 2;
			optionsPanelConstraints.gridy = 4;
			optionsPanelConstraints.insets = new Insets(0, 0, 0, 35);
			
			JButton butChoose = new JButton("choose file");
			butChoose.setFont(new Font("TimesRoman", Font.ITALIC, 20));
			butChoose.setBorderPainted(false);
			butChoose.setFocusable(false);
			butChoose.setBackground(new Color(230,230,0));
			butChoose.setForeground(new Color(240,255,240));
			butChoose.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser fc = new JFileChooser("C:/Users/Lenovo/Desktop");
					fc.showDialog(null, "0k");
					try{
						readPoints(fc.getSelectedFile());
					}
					catch(NullPointerException e){}
				}
			});
			super.add(butChoose, optionsPanelConstraints);
			
			
			optionsPanelConstraints.fill = GridBagConstraints.BOTH;
			optionsPanelConstraints.gridx = 0;
			optionsPanelConstraints.gridy = 5;
			optionsPanelConstraints.gridwidth = 4;
			optionsPanelConstraints.insets = new Insets(-30, 5, 0, 0);
			
			JLabel lPoints = new JLabel("Points:");
			lPoints.setFont(new Font("TimesRoman", Font.TYPE1_FONT, 20));
			lPoints.setForeground(new Color(240,255,240));
			super.add(lPoints, optionsPanelConstraints);
			
			
			optionsPanelConstraints.weighty = 5;
			optionsPanelConstraints.gridy = 6;
			optionsPanelConstraints.insets = new Insets(-10, 15, 0, 15);
			
			model = new DefaultListModel();	
			allPoints = new JList(model);
			JScrollPane scrollPane = new JScrollPane(allPoints);
			super.add(scrollPane, optionsPanelConstraints);
			
			optionsPanelConstraints.weighty = 1;
			optionsPanelConstraints.gridy = 7;
			optionsPanelConstraints.insets = new Insets(0, 5, -10, 0);
			
			lines = new JLabel();
			changeLinesCounter(0);
			lines.setFont(new Font("TimesRoman", Font.TYPE1_FONT, 20));
			lines.setForeground(new Color(240,255,240));
			super.add(lines, optionsPanelConstraints);
			
			optionsPanelConstraints.gridx=2;
			optionsPanelConstraints.insets = new Insets(0, 0, 0, 0);
			optionsPanelConstraints.fill = GridBagConstraints.NONE;
			
			JButton butStart = new JButton("Go->");
			butStart.setFont(new Font("TimesRoman", Font.ITALIC, 20));
			butStart.setBorderPainted(false);
			butStart.setFocusable(false);
			butStart.setBackground(new Color(102,255,51));
			butStart.setForeground(new Color(240,255,240));
			butStart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					actionPanel.programeBrain();
				}
			});
			super.add(butStart, optionsPanelConstraints);
		}
		
		public void readPoints(File file) {
			try {
				Scanner scanner = new Scanner(file);
	            int capacity = scanner.nextInt();
	            for(int i=0; i<capacity; ++i) {
	                Poiint newP = new Poiint(scanner.nextInt(), scanner.nextInt());
	                model.addElement(newP.toString());
					actionPanel.addPoint(newP);
	            }
	        } catch (Exception e) {}
	    }
	}
	
	public static void changeLinesCounter(int c)
	{
		lines.setText("Lines: " + c);
	}
}