package gui.components.sub;

import gui.manager.GuiManager;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class SingleOrMutliPanel extends JPanel {


	public SingleOrMutliPanel(){
		setUpPanel();
	}

	private void setUpPanel(){

		setLayout(new MigLayout("fill"));

		JButton singleButton = new JButton("Singleplayer");
		singleButton.addActionListener(GuiManager.getInstance());
		JButton multiButton = new JButton("Multiplayer");
		multiButton.addActionListener(GuiManager.getInstance());
		singleButton.setBackground(Color.WHITE);
		multiButton.setBackground(Color.WHITE);
		add(singleButton, "h 50%, w 50%, span, split, center");
		add(multiButton, "h 50%, w 50%");
		setOpaque(false);
		setVisible(true);
		revalidate();
	}
}
