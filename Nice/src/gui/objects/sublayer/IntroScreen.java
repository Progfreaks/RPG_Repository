package gui.objects.sublayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import gui.GuiEventCreator.EVENT_TYPE;
import gui.GuiEventCreator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class IntroScreen {

	private JPanel introPanel;
	private JButton newGButton; // ActionListener new gme
	private JButton loadGButton; // ACtionListener load game
	private GuiEventCreator eCreator;

	public IntroScreen(){
		
		eCreator = new GuiEventCreator(EVENT_TYPE.DEFAULT);

		introPanel = new JPanel(new MigLayout("fill"));
		newGButton = new JButton("New Game");
		loadGButton = new JButton("Load Game");
		newGButton.setBackground(Color.WHITE);
		loadGButton.setBackground(Color.WHITE);
		eCreator.setStartCall(newGButton); //GuiStartEvent !StartEvent
		introPanel.add(newGButton, "h 50%, w 50%, span, split, center");
		introPanel.add(loadGButton, "h 50%, w 50%");
		introPanel.setOpaque(false);
		introPanel.setVisible(true);
		introPanel.revalidate();

	}

	public JPanel getIntroScreen(){

		return introPanel;

	}



	public BufferedImage loadImg(String path){

		try{
			File input = new File(path);
			BufferedImage image = ImageIO.read(input);;
			return image;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;



	}


}
