package domain;



import domain.DuD;
import gui.*;
import gui.guiobjects.BackLayer;

public class GuiController {
	private DuD game;
	private Maprender map;
	private GuiManager gui;
	public GuiController(DuD game){
		this.game = game;
	}
	
	public void removePanel(int i){
		gui.removePanel(i);
	}
	
	public void addPanel(Object obj, int i, String in){
		gui.addPanel(obj, i, in);
	}
	
	public void refresh(){
		gui.refresh();
	}
	public void fillLayer(int index, int i, int s){
		gui.fillLayer(index, i, s);
	}
	public void setGuiMgr(GuiManager mgr){
		gui = mgr;
	}
	public void setPanelVisible(int i, boolean in){
		gui.setPanelVisible(i, in);
		
	
	}
	public BackLayer getBackLayer(){
		return gui.getBackLayer();
	}
	public void renderGUI(){
		gui.renderGUI();
	}
}
