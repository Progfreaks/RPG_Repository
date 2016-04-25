package valueobject.guiobjects;

import javax.swing.*;

import valueobject.fieldobjects.TestFigure;

import java.awt.Color;
import java.awt.GridLayout;


public class ButtonLayer {
	public JButton[][] fieldSquares = new JButton[32][32];
	private JPanel buttonLayer;
	public ButtonLayer(){
		buttonLayer = new JPanel(new GridLayout(32,32));
		
	}
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
