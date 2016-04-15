package domain;

import valueobject.events.GameEvent;
import domain.MapHandling;
import javax.swing.JButton;
import gui.Maprender;

public class DuD {
	public DuD game = null;
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
	public DuD getGame(){

		return this.game;
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
	
}
