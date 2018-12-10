/**
 * @author Nazar Kostiuk
 */

import javax.swing.*;

import java.awt.event.*;

public class Main extends JFrame implements KeyListener{	
	
	int width = 500, height = 500;
	Draw draw;
	
	public static void main(String args[]){
		new Main();
	}
	
	public Main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle("Elastic Collisions");
        
        draw = new Draw(width, height);
        add(draw);

        setResizable(true);
        setVisible(true);
        
        Action updateCursorAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	draw.forever();
            	draw.repaint();
            }
        };
        
        this.addKeyListener(this);
        draw.addKeyListener(this);

        new Timer(10, updateCursorAction).start();
	}

	public void keyPressed(KeyEvent ke) {
		draw.keyDown(ke.getKeyCode());
	}

	public void keyReleased(KeyEvent ke) {
		
	}

	public void keyTyped(KeyEvent ke) {
		
	}

}