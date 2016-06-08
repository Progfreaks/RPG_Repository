package gui.components.sub;

import gui.manager.GuiGameCycle;
import gui.manager.GuiGameCycle.PHASE;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	
	private JMenuBar menuBar;
	private JButton rollButton;
	private int rounds;
	
	/**
	 * Konstruktor
	 */
	public MenuPanel() {
		
		setUpPanel();
		
	}
	
	private void setUpPanel(){
		menuBar = new JMenuBar();
		rollButton = new JButton("Please Roll");
		//Macht den Button durchsichtig
		rollButton.setContentAreaFilled(false);
		rollButton.setBorderPainted(false);
		//rollButton.addActionListener(new GuiMapEventPerformer(EVENT_TYPE.ROLL));
		rollButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiGameCycle.getInstance().setPhase(PHASE.ROLL);
				GuiGameCycle.getInstance().wake();
				GuiGameCycle.getInstance().setClickedCoordinate(e);
				
			}
		});
		menuBar.setBackground(Color.BLUE);
		menuBar.add(rollButton);
		add(menuBar);
		setSize(400, 200);
	    setVisible(true);
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
