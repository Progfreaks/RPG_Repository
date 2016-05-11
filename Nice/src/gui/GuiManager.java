package gui;

import gui.guiobjects.*;
import javax.swing.*;
import gui.*;
import domain.CentralSave;
import domain.DuD;


public class GuiManager {
	private BackLayer backLayer;
	private ButtonLayer buttonLayer;
	private MenuLayer menuLayer;
	private CommandoInput console;
	private Maprender map;
	private static DuD game;
	
	public GuiManager(BackLayer bL){
		this.backLayer = bL;
		
	}
	
	/**
	 * i = index in der panellist
	 * @param i
	 */
	public void removePanel(int i){
		backLayer.removePanel(i);	
	}
	
	public void addPanel(Object obj, int i, String in){
		JPanel p = (JPanel) obj;
		backLayer.addPanel(p, i, in);
	}
	
	
	
	/**
	 * Macht das Frame sichtbar, true = anzeigen
	 * @param in
	 */
	public void setVisible(boolean in){
		backLayer.setVisible(in);
	}
	
	/**
	 * i = index des anzuzeigenden panel
	 * @param i
	 * @param in
	 */
	public void setPanelVisible(int i, boolean in){
		backLayer.setPanelVisible(i, in);
	}
	
	public void refresh(){
		backLayer.refresh();
	}
	
	/**
	 * index = zum switchen der verschiedenen Felder, i und s = array positions
	 * @param index
	 * @param i
	 * @param s
	 */
	public void fillLayer(int index, int i, int s){
		buttonLayer.fillLayer(index, i, s);
	}
	
	public JPanel getButtonLayer(){
		return buttonLayer.getButtonLayer();
	}
	public JPanel getMenuLayer(){
		return menuLayer.getMenuLayer();
	}
	
	public void newMaprender(BackLayer backLayer, ButtonLayer buttonLayer){
		map = new Maprender(backLayer, buttonLayer);
	}
	public void setbackLayer(BackLayer bL){
		this.backLayer = bL;
	}
	public BackLayer getBackLayer(){
		return backLayer;
	}
	public void renderGUI(){
		buttonLayer = new ButtonLayer();
		menuLayer = new MenuLayer();
		console = new CommandoInput();
		CentralSave.setConsole(console);
		backLayer.createMainPanel(buttonLayer, console, menuLayer);
	}
	
	public static void main(String[] args){
		game = new DuD();
		game.setGame(game);
		new StartGui();
	}
	
}
