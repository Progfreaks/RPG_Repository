package gui.components.sub;

import gui.manager.GuiManager;
import gui.manager.GuiMapHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import domain.DuD;

@SuppressWarnings("serial")
public class GameStatePanel extends JPanel {



	public GameStatePanel(){
		JButton saveButton = new JButton("save");
		saveButton.addActionListener(GuiManager.getInstance());
		add(saveButton);


	}


}
