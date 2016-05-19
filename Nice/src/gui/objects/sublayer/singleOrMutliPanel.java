package gui.objects.sublayer;

import gui.creater.GuiManager;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class singleOrMutliPanel extends JPanel {

	
	public singleOrMutliPanel(){
		setUpPanel();
	}
	
	private void setUpPanel(){
		
		
		setLayout(new BorderLayout());
		
		//JPanel imagePanel = new JPanel();
		JPanel selectLayer = new JPanel();
		JButton singleButton = new JButton("Singleplayer");
		singleButton.addActionListener(GuiManager.getInstance());
		JButton multiButton = new JButton("Multiplayer");
		singleButton.setBackground(Color.RED);
		singleButton.setForeground(Color.BLACK);
		singleButton.setVisible(true);
		
		selectLayer.setLayout(new BorderLayout());
		selectLayer.add(singleButton, BorderLayout.WEST);
		selectLayer.add(multiButton, BorderLayout.EAST);
		selectLayer.setVisible(true);
		//imagePanel.add(new JLabel(new ImageIcon(loadImg("resource/images/introscreen.jpeg"))));
		//imagePanel.setVisible(true);
		selectLayer.setOpaque(false);
		//add(imagePanel,BorderLayout.SOUTH);
		add(selectLayer,BorderLayout.NORTH);
		//introPanel.setSize(1024, 768);
		setVisible(true);
		revalidate();
	}
}
