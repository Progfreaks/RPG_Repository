package gui.objects;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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
		setSize(1200, 1000);
		setLayout(new BorderLayout());
	}

	
	



	

}



