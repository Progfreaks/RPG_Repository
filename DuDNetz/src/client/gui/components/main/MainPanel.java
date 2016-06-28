package client.gui.components.main;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel{
	
	public MainPanel(){
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new MigLayout("fill"));
//		setSize(1024, 768);
	//	setSize(WindowSize.getWindowWidth()/2, WindowSize.getWindowHeight()/2);
		setBackground(Color.black);
		setOpaque(false);
	}

}
