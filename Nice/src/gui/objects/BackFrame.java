package gui.objects;

import gui.MyConsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.DuD;
import net.miginfocom.swing.MigLayout;

public class BackFrame {

	private JFrame backLayer;
	private JPanel mainPanel;

	private ArrayList<JPanel> panelList;

	public BackFrame() {

		this.backLayer = new JFrame("Bestes RPG");
		backLayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backLayer.setSize(1000, 1000);
		backLayer.setLayout(new BorderLayout());
		panelList = new ArrayList<JPanel>();
		mainPanel = new JPanel();
		backLayer.add(mainPanel, BorderLayout.CENTER);
		backLayer.setBackground(Color.BLACK);
		backLayer.setVisible(true);

		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/**
		 * int w = screenSize.width; int h = screenSize.height;
		 * backLayer.setBounds(w / 4, h / 4, w / 2, h / 2);
		 **/

		// backLayer.setSize(500, 500);

		// BoxLayout boxlayout = new BoxLayout(backLayer.getContentPane(),
		// BoxLayout.Y_AXIS);

	}

	public void setbackLayer(JFrame backLayer) {

		this.backLayer = backLayer;

	}

	public BackFrame getbackLayer() {
		return this;
	}

	public void removePanel(int i) {

		backLayer.remove(panelList.get(i));

		this.panelList.remove(i);
		
		mainPanel.remove(i);

	}
	
	public int panelSize(){
		
		return panelList.size();
		
	}

	public void addPanel(JPanel panel, int i,String in) {

		this.panelList.add(i, panel);

		mainPanel.add(panel, in, i);

		backLayer.revalidate();

	}

	public void setVisible(boolean in) {

		backLayer.setVisible(in);

	}

	public void setPanelVisible(int i, boolean in) {

		panelList.get(i).setVisible(in);

	}

	public void refresh() {
		
		backLayer.revalidate();
		backLayer.repaint();

	}


	public void createMainPanel(ButtonPanel buttonLayer, MenuPanel menuLayer) {

	
		mainPanel.setLayout(new MigLayout("fill"));
		mainPanel.setSize(1024, 768);
		mainPanel.setBackground(Color.black);
		addPanel(buttonLayer.getButtonPanel(), 0, "push, height 400:400:400, width 400:400:400");
		addPanel(menuLayer.getMenuPanel(), 1, "w 20%");
		//addPanel(console.getConsole(), 2, ("w 50%, wrap"));
		addPanel(DuD.getGame().getConsole().getConsole(), 2, ("w 50%, wrap"));
		mainPanel.setVisible(true);
		backLayer.add(mainPanel);
		refresh();

	}
	
	public void createOpeningPanel(){
		
		mainPanel.setLayout(new MigLayout("fill"));
		mainPanel.setSize(1024, 768);
		mainPanel.setBackground(Color.black);
		addPanel(DuD.getGame().getConsole().getConsole(),0, ("w 100%, wrap"));
		mainPanel.setVisible(true);
		backLayer.add(mainPanel);
		refresh();
	}
	
	

	}
	


