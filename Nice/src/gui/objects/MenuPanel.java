package gui.objects;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MenuPanel {
	
	JPanel menuPanel;
	JMenuBar menuBar;
	JButton rollButton;
	private int rounds;
	
	/**
	 * Konstruktor
	 */
	public MenuPanel(){
		
		menuPanel = new JPanel();
		menuBar = new JMenuBar();
		rollButton = new JButton("Please Roll");
		menuBar.add(rollButton);
		menuPanel.add(menuBar);
		menuPanel.setSize(400, 200);
		menuPanel.setVisible(true);
		rounds = 0;
	}
	
	public JPanel getMenuPanel(){
		
		return menuPanel;
	}
	
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	public JButton getRollButton(){
		
		return rollButton;
		
	}
	
	public void setRollButton(JButton button){
		
		rollButton = button;
		
	}
	public int raiseIndex(){
		
		rounds++;
		return rounds;
		
	}

}