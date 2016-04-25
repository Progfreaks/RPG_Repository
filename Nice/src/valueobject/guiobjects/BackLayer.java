package valueobject.guiobjects;
import javax.swing.*;
import java.util.ArrayList;

public class BackLayer {
	private JFrame backLayer;
	private ArrayList<JPanel> panelList;
	public BackLayer(){
		this.backLayer = new JFrame("Bestes RPG");
		backLayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backLayer.setSize(1240, 1024);
		BoxLayout boxlayout = new BoxLayout(backLayer.getContentPane(), BoxLayout.Y_AXIS);
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

}
