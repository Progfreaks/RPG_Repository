package persistence.character;


import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Erzeugt ein GUI mit Experience-Table
 * @author YOU_HEY
 *
 */
public class StartCharacterData extends JFrame{

	//private static final long serialVersionUID = 
	
	private static final long serialVersionUID = -8492414654249932472L;

	public  StartCharacterData(String pTitle){
		
		super(pTitle);
		setSize(new Dimension(700, 600));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		getContentPane().add(new MainCharacterDataPanel());
		
		setVisible(true);
	}
	
	public static void main(String args[]){

		new StartCharacterData("Character data");
	}
}
