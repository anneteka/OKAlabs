package PW6;

import javax.swing.SwingUtilities;

public class Starter
{

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				new Window();
			}
		});
	}

}