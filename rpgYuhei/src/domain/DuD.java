package domain;

import valueobject.events.GameEvent;
import valueobject.fieldobjects.*;
import domain.MapHandling;
import javax.swing.JButton;
import gui.Maprender;
import domain.PlayerMgr;

public class DuD {
	public static DuD game = null;
	public MapHandling BoardMgr = null;
	
	
	public DuD(){
		this.BoardMgr = new MapHandling();
	}
	
		
	

	// verteilt Anfragen an
	// - BoardManager
	// - PlayerManager
	// - GameLogic
	// - ...
	
	public void processEvent(GameEvent ev) {
		ev.process();
	}
	public void gameCycle(){
		
		
	}
	public static DuD getGame(){

		return game;
	}
	public void setGame(DuD game){
		this.game = game;
	}
	public JButton[][] getMap(){ //Anfrage der Map an MapHandling
		return BoardMgr.render();
	}
	public int[][] getBoardArray(){
		return BoardMgr.getBoardArray();
	}
	/**
	public FieldObjects[][] getFieldObjects(){
		return BoardMgr.getFieldObjects();
	}
	*/
	public JButton[][] getButtonArray(){
		return BoardMgr.getButtonArray();
	}
	public TestFigure getPlayer(){
		return BoardMgr.getPlayer();
	}
	public void recolour(int xf,int yf){
		BoardMgr.recolour(xf, yf);
	}
}

