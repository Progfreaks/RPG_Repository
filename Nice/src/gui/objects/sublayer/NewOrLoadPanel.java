package gui.objects.sublayer;

import gui.creater.GuiManager;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NewOrLoadPanel extends JPanel{

	public NewOrLoadPanel(){
		setUpPanel();
	}
	
	private void setUpPanel()
	{
		JButton newGameButton = new JButton("new game");
		JButton loadGameButton = new JButton("load game");
		newGameButton.addActionListener(GuiManager.getInstance());
		add(newGameButton);
		add(loadGameButton);
		
	}
}
