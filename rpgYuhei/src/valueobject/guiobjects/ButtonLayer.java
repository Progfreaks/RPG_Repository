package valueobject.guiobjects;

import javax.swing.*;

import valueobject.fieldobjects.TestFigure;

import java.awt.Color;
import java.awt.GridLayout;



/**
 * Button Layer Klasse.
 * 
 * @author YOU_HEY
 *
 */
public class ButtonLayer {
	//public JButton[][] fieldSquares = new JButton[32][32];
	public JButton[][] fieldSquares = new JButton[16][32];

	private JPanel buttonLayer;
	public ButtonLayer(){
		//buttonLayer = new JPanel(new GridLayout(32,32));
		buttonLayer = new JPanel(new GridLayout(16,32));

	}
	
	/**
	 * jedes Feld wird mit entsprechender Farbe ausgefuellt.
	 * @param index
	 * @param i
	 * @param s
	 */
	public void fillLayer(int index, int i, int s){
		switch(index)
		{
		case 0: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.BLACK); 
		fieldSquares[i][s].setOpaque(true); addButton(fieldSquares[i][s]); break;
		
		case 1: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.YELLOW); 
		fieldSquares[i][s].setOpaque(true); addButton(fieldSquares[i][s]); break;
		
		case 2: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.CYAN); 
		fieldSquares[i][s].setOpaque(true); addButton(fieldSquares[i][s]); break;
		case 3: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.RED); 
		fieldSquares[i][s].setOpaque(true); addButton(fieldSquares[i][s]); break;
		
		case 4: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.BLUE); 
		 fieldSquares[i][s].setOpaque(true); addButton(fieldSquares[i][s]);  break;
		}
		
	}
	public JButton[][] getFieldSquares(){
		return fieldSquares;
	}
	public void addButton(JButton square){
		buttonLayer.add(square);
	}
	public void setVisible(boolean in){
		buttonLayer.setVisible(in);
	}
	public JPanel getButtonLayer(){
		return buttonLayer;
	}
	public void disable(JButton square){
		square.setEnabled(false);
	}
	public void enable(JButton square){
		square.setEnabled(true);
	}
	

}
