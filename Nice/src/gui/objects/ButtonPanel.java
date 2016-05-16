package gui.objects;

import javax.swing.*;
import javax.swing.border.*;

import valueobject.fieldobjects.TestFigure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;


public class ButtonPanel extends JPanel{
	
	public  JButton[][] buttons = new JButton[32][32];

	//private JPanel buttonPanel;
	private Border moveBorder;
	
	/**
	 * Konstruktor dieser Klasse.
	 */
	public ButtonPanel(){
		
		setLayout(new GridLayout(32,32));
		

		
	}
	
	public void setButtonMatrix(JButton[][] buttons){
		this.buttons = buttons;
	}
	
	
	public void paintButtons(int index, int i, int s){
		switch(index)
		{
		case 0: setColorButton(i, s, Color.BLACK);
		
		break;
		
		case 1: setColorButton(i, s, Color.LIGHT_GRAY);
			
		break;
		
		case 2: setColorButton(i, s, Color.CYAN);
		
		break;
		
		case 3: setColorButton(i, s, Color.RED);
		
		break;
		
		case 4: setColorButton(i, s, Color.BLUE);
		
		 break;
		}
		
		
		
	}
	
	/**
	 * Hilfs Methode fuere fillLayer(int index, int i, int s)
	 * @param i
	 * @param s
	 * @param c
	 */
	private  void setColorButton(int i, int s, Color c){
		
	buttons[i][s] = new JButton(); 
	buttons[i][s].setBackground(c); 
	buttons[i][s].setOpaque(true); 
	addButton(buttons[i][s]); 
	
	}
	
	public JButton getButton(int i, int s){
		return buttons[i][s];
	}
	
	public JButton[][] getButtonMatrix(){
		return buttons;
	}
	
	public void addButton(JButton button){
		
		add(button);
	}
	
	public void setVisible(boolean in){
		setVisible(in);
	}
	
	public JPanel getButtonPanel(){
		return this;
	}
	
	public void disable(JButton button){
		button.setEnabled(false);
	}
	
	public void enable(JButton button){
		
		button.setEnabled(true);
		
	}
	
	public void setBorder(JButton button){
		moveBorder = new LineBorder(Color.GREEN, 1);
		button.setBorder(moveBorder);
		button.setBorderPainted(true);
	}
	
	public void removeBorder(JButton button){
		moveBorder = new LineBorder(Color.GRAY);
		button.setBorder(moveBorder);
		button.setBorderPainted(true);
	}
	

}
