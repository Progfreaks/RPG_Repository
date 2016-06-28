package client.gui.components.sub;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import client.gui.manager.GuiManager;

@SuppressWarnings("serial")
public class GameStatePanel extends JPanel {



	
	private JMenuBar menuBar;
	private JButton saveButton ;
	
	/**
	 * Konstruktor
	 */
	public GameStatePanel() {
		
		setUpPanel();
		
	}
	
	private void setUpPanel(){
		menuBar = new JMenuBar();
		saveButton = new JButton("save");
		saveButton.addActionListener(GuiManager.getInstance());
		add(saveButton);

		//Macht den Button durchsichtig
		saveButton.setContentAreaFilled(false);
		saveButton.setBorderPainted(false);
		menuBar.setBackground(Color.RED);
		menuBar.add(saveButton);
		add(menuBar);
		setSize(400, 200);
	    setVisible(true);
	    setOpaque(false);
	}
	
	


}
