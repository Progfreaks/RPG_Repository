package gui.objects.mainlayer;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel{
	
	public MainPanel(){
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new MigLayout("fill"));
		setSize(1024, 768);
		setBackground(Color.black);
		setOpaque(false);
	}

}
