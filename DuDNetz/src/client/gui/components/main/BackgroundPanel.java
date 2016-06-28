package client.gui.components.main;

import java.awt.Graphics;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class BackgroundPanel extends JPanel{


	private String path;

	public BackgroundPanel(String path){
		this.path = path;
		setUpPanel();
	}



	private void setUpPanel(){
		setLayout(new MigLayout("fill"));
		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(path.equals("")){
			super.paintComponent(g);
		}else{
			ImagePainter.paintPanel(g, this, path);
		}


	}



}
