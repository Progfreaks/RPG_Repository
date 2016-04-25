package parsistence;


import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Erzeugt ein GUI mit Experience-Table
 * @author YOU_HEY
 *
 */
public class StartExperienceTable extends JFrame{

	//private static final long serialVersionUID = 
	
	private static final long serialVersionUID = -8492414654249932472L;

	public  StartExperienceTable(String pTitle){
		super(pTitle);
		setSize(new Dimension(600, 300));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new ExperienceTablePanel());

		
		setVisible(true);
	}
	
	public static void main(String args[]){

		new StartExperienceTable("Experience Table Maker");
	}
}
