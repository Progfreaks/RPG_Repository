package valueobject.guiobjects;
import javax.swing.*;

import org.omg.CORBA.PUBLIC_MEMBER;

import domain.GameCycle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class BackLayer {
	private JFrame backLayer;
	private ArrayList<JPanel> panelList;
	public BackLayer(){
		this.backLayer = new JFrame("Bestes RPG");
		backLayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/** int w = screenSize.width;
		        int h = screenSize.height;
		        backLayer.setBounds(w / 4, h / 4, w / 2, h / 2);**/
		backLayer.setSize(1000, 600);
		
		
		//backLayer.setSize(500, 500);

		//BoxLayout boxlayout = new BoxLayout(backLayer.getContentPane(), BoxLayout.Y_AXIS);
		BorderLayout boxlayout = new BorderLayout();
		backLayer.setLayout(boxlayout);
		this.panelList = new ArrayList<JPanel>();
		backLayer.setVisible(true);
	}
	public void setbackLayer(JFrame backLayer){
		this.backLayer = backLayer;
	}
	public void removePanel(int i){
		backLayer.remove(panelList.get(i));
		this.panelList.remove(i);
		
	}
	public void addPanel(JPanel newPanel, int i){
		this.panelList.add(i, newPanel);
		backLayer.add(newPanel, i);
		backLayer.revalidate();
	}
	public void setVisible(boolean in){
		backLayer.setVisible(in);
	}
	public void setPanelVisible(int i, boolean in){
		panelList.get(i).setVisible(in);
	}
	public void refresh(){
		backLayer.revalidate();
	}
	public void addMenuBar(JMenuBar menuBar){
		backLayer.add(menuBar, BorderLayout.SOUTH);
		refresh();
	}

}
