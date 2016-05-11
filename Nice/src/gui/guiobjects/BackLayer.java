package gui.guiobjects;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import gui.CommandoInput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

public class BackLayer {
	private JFrame backLayer;
	private JPanel mainPanel;
	private ArrayList<JPanel> panelList;
	public BackLayer(){
		this.backLayer = new JFrame("Bestes RPG");
		backLayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backLayer.setSize(1024, 768);
		backLayer.setLayout(new BorderLayout());
		this.panelList = new ArrayList<JPanel>();
		mainPanel = new JPanel();
		backLayer.add(mainPanel, BorderLayout.CENTER);
		backLayer.setBackground(Color.BLACK);
		backLayer.setVisible(true);
	}
	public void setbackLayer(JFrame backLayer){
		this.backLayer = backLayer;
	}
	public void removePanel(int i){
		mainPanel.remove(panelList.get(i));
		this.panelList.remove(i);
		mainPanel.revalidate();
		refresh();
		
	}
	public void addPanel(JPanel newPanel, int i, String in){
		this.panelList.add(i, newPanel);
		mainPanel.add(newPanel, in);
		backLayer.revalidate();
		backLayer.repaint();
	}
	public void setVisible(boolean in){
		backLayer.setVisible(in);
	}
	public void setPanelVisible(int i, boolean in){
		panelList.get(i).setVisible(in);
	}
	public void refresh(){
		backLayer.revalidate();
		backLayer.repaint();
	}
	public BackLayer getbackLayer(){
		return this;
	}
	public void setConsole(){
		
	}
	public void createMainPanel(ButtonLayer bl, CommandoInput ci, MenuLayer ml){
		
		mainPanel.setLayout(new MigLayout("fill"));
		mainPanel.setSize(1024, 768);
		addPanel(bl.getButtonLayer(), 0, "grow, push, span");
		addPanel(ml.getMenuLayer(), 1, "w 50%");
		addPanel(ci.getConsole(), 2,("w 50%, wrap"));
		mainPanel.setVisible(true);
		backLayer.add(mainPanel);
		refresh();
		
		
	}


}
