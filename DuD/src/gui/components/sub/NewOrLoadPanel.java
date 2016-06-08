package gui.components.sub;

import java.awt.Color;

import gui.manager.GuiManager;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class NewOrLoadPanel extends JPanel {

	
	
	public NewOrLoadPanel(){
		setUpPanel();
	}
	
	private void setUpPanel()
	{
		
		setLayout(new MigLayout("fill"));
		JButton newGameButton = new JButton("New game");
	    JButton loadGameButton = new JButton("Load game");
	    newGameButton.setBackground(Color.WHITE);
		loadGameButton.setBackground(Color.WHITE);
		newGameButton.addActionListener(GuiManager.getInstance());
		loadGameButton.addActionListener(GuiManager.getInstance());
		
		add(newGameButton, "h 50%, w 50%, span, split, center");
		add(loadGameButton, "h 50%, w 50%");
		setOpaque(false);
		setVisible(true);
	    revalidate();
		
	}
	
	
	

	
}
