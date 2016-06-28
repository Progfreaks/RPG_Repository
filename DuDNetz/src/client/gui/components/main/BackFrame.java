package client.gui.components.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import client.gui.components.sub.WindowSize;

/**
 * Diese Klasse repraesentiert das Backframe.
 * @author YOU_HEY
 *
 */
public class BackFrame extends JFrame{


	public BackFrame(String title) {
		super(title);
		setUpFrame();
	}
	
	/**
	 * Initialisiert das Backframe.
	 */
	private void setUpFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(1200, 1000);
		setSize(WindowSize.getWindowWidth(2), WindowSize.getWindowHeight(1));
		setLayout(new BorderLayout());
	}

	
	



	

}



