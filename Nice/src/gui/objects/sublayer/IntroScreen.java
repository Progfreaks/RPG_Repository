package gui.objects.sublayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroScreen {

	private JPanel introPanel;
	private JButton singleButton;
	private JButton multiButton;

	public IntroScreen(){

		introPanel = new JPanel();
			
		introPanel.setLayout(new BorderLayout());
		
		JPanel imageLayer = new JPanel();
		JPanel selectLayer = new JPanel();
		singleButton = new JButton("Singleplayer");
		multiButton = new JButton("Multiplayer");
		singleButton.setBackground(Color.RED);
		singleButton.setForeground(Color.BLACK);
		selectLayer.setLayout(new BorderLayout());
		selectLayer.add(singleButton, BorderLayout.WEST);
		selectLayer.add(multiButton, BorderLayout.EAST);
		selectLayer.setVisible(true);
		imageLayer.add(new JLabel(new ImageIcon(loadImg("resource/images/introscreen.jpeg"))));
		imageLayer.setVisible(true);
		selectLayer.setOpaque(false);
		introPanel.add(imageLayer,BorderLayout.SOUTH);
		introPanel.add(selectLayer,BorderLayout.NORTH);
		//introPanel.setSize(1024, 768);
		introPanel.setVisible(true);
		introPanel.revalidate();


	}

	public JPanel getIntroScreen(){

		return introPanel;

	}

	public JButton getSingleButton(){
		return singleButton;
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
