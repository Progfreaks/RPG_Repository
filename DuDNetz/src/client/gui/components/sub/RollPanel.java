package client.gui.components.sub;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import client.gui.manager.GuiGameCycle.PHASE;
import client.net.Facade;

public class RollPanel extends JPanel{
	
	private JMenuBar menuBar;
	private JButton rollButton;
	private int rounds;
	private Facade facade;
	
	/**
	 * Konstruktor
	 */
	public RollPanel(Facade facade) {
		
		setUpPanel();
		this.facade = facade;
	}
	
	private void setUpPanel(){
		menuBar = new JMenuBar();
		rollButton = new JButton("Please Roll");
		//Macht den Button durchsichtig
		rollButton.setContentAreaFilled(false);
		rollButton.setBorderPainted(false);
		rollButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				facade.sendPhase("ROLL");
			}
		});
		menuBar.setBackground(Color.BLUE);
		menuBar.add(rollButton);
		add(menuBar);
		setSize(400, 200);
	    setVisible(true);
	    setOpaque(false);
		rounds = 0;
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
