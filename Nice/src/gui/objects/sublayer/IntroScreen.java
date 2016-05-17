package gui.objects.sublayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroScreen {

	private JPanel introScreen;
	private JButton single;
	private JButton multi;

	public IntroScreen(){

		introScreen = new JPanel();
		introScreen.setLayout(new BorderLayout());
		JPanel imageLayer = new JPanel();
		JPanel selectLayer = new JPanel();
		single = new JButton("Singleplayer");
		multi = new JButton("Multiplayer");
		single.setBackground(Color.RED);
		single.setForeground(Color.BLACK);
		selectLayer.setLayout(new BorderLayout());
		selectLayer.add(single, BorderLayout.WEST);
		selectLayer.add(multi, BorderLayout.EAST);
		selectLayer.setVisible(true);
		imageLayer.add(new JLabel(new ImageIcon(loadImg("resource/images/introscreen.jpeg"))));
		imageLayer.setVisible(true);
		selectLayer.setOpaque(false);
		introScreen.add(imageLayer,BorderLayout.SOUTH);
		introScreen.add(selectLayer,BorderLayout.NORTH);
		introScreen.setSize(1024, 768);
		introScreen.setVisible(true);
		introScreen.revalidate();


	}

	public JPanel getIntroScreen(){

		return introScreen;

	}

	public JButton getSingleButton(){
		return single;
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
