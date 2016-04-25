package domain;
import javax.swing.JButton;
import javax.swing.JPanel;
import valueobject.guiobjects.*;

import domain.DuD;
import gui.*;

public class GuiController {
	private DuD game;
	private BackLayer backLayer;
	private RollButton rollButton;
	private Maprender map;
	public GuiController(DuD game){
		
		this.game = game;
	}
	public void renderMap(){
	}
	public void setbackLayer(BackLayer backLayer){
		this.backLayer = backLayer;
	}
	public BackLayer getbackLayer(){
	return this.backLayer;
	}
	public void removePanel(int i){
		backLayer.removePanel(i);
	}
	public void addPanel(JPanel obj, int i){
		backLayer.addPanel(obj, i);
	}
	public void newMaprender(DuD game, BackLayer backLayer, ButtonLayer buttonLayer){
		map = new Maprender(game, backLayer, buttonLayer);
	}
	public void refresh(){
		backLayer.refresh();
		map.refreshGUI();
	}
	public void setRollButton(RollButton rollButton){
		this.rollButton = rollButton;
	}
	
	public JPanel getRollLayer(){
		return rollButton.getRollLayer();
	}
	
	public JButton getRollButton(){
		return rollButton.getRollButton();
	}

}
