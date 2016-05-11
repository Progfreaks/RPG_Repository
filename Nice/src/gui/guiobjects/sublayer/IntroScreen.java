package gui.guiobjects.sublayer;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import domain.DuD;

public class IntroScreen {
	private static DuD game;
	private JPanel introScreen;
	public IntroScreen(){
		game = DuD.getGame();
		introScreen = new JPanel();
		introScreen.setLayout(new BorderLayout());
		JPanel imageLayer = new JPanel();
		JPanel selectLayer = new JPanel();
		JButton single = new JButton("Singleplayer");
		JButton multi = new JButton("Multiplayer");
		selectLayer.setLayout(new BorderLayout());
		selectLayer.add(single, BorderLayout.WEST);
		selectLayer.add(multi, BorderLayout.EAST);
		selectLayer.setVisible(true);
		imageLayer.add(new JLabel(new ImageIcon(game.loadImg("resource/images/introscreen.jpeg"))));
		imageLayer.setVisible(true);
		selectLayer.setOpaque(false);
		introScreen.add(imageLayer);
		introScreen.add(selectLayer);
		introScreen.setSize(1024, 768);
		introScreen.setVisible(true);
		introScreen.revalidate();
	}
	public JPanel getIntroScreen(){
		return introScreen;
	}
	

}
