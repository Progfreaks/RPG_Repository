package domain;

import valueobject.events.GameEvent;
import valueobject.fieldobjects.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import domain.*;
import valueobject.guiobjects.*;
import valueobject.character.Character;
import domain.GameCycle;

public class DuD {
	public static DuD game = null;
	private MapHandling BoardMgr = null;
	private GuiController GuiControll = null;
	private CharacterManager CharMgr = null;
	private GameCycle cycle;
	
	public DuD(){
		this.BoardMgr = new MapHandling(this);
		this.GuiControll = new GuiController(this);
		this.CharMgr = new CharacterManager(this);
	}
	
		
	

	// verteilt Anfragen an
	// - BoardManager
	// - PlayerManager
	// - GameLogic
	// - ...
	
	public void processEvent(GameEvent ev) {
		ev.process();
	}
	
	public void startGame(){
		cycle = new GameCycle(this);
		
	}
	
	public static DuD getGame(){

		return game;
	}
	
	public void setGame(DuD game){
		this.game = game;
	}
	
	public int[][] getBoardArray(){
		return BoardMgr.getBoardArray();
	}
	
	public JButton[][] getButtonArray(){
		return BoardMgr.getButtonArray();
	}
	
	public void recolour(int xf,int yf){
		BoardMgr.recolour(xf, yf);
	}
	
	public void renderMap(){
		BoardMgr.render();
		
	}
	public void callForAction(int diceNum){
		BoardMgr.callForAction(diceNum);
	}
	
	public void setbackLayer(BackLayer backLayer){
		GuiControll.setbackLayer(backLayer);
	}
	
	public BackLayer getbackLayer(){
		
		return GuiControll.getbackLayer();
	
	}
	
	public void removePanel(int i){
		GuiControll.removePanel(i);
	}
	
	public void addPanel(JPanel obj, int i){
		GuiControll.addPanel(obj, i);
	}
	public void refreshGUI(){
		GuiControll.refresh();
	}
	
	public void newMaprender(DuD game, BackLayer backLayer, ButtonLayer buttonLayer){
		GuiControll.newMaprender(game,backLayer,buttonLayer);
	}
	
	public void setRollButton(RollButton rollButton){
		GuiControll.setRollButton(rollButton);
	}
	
	public JButton getRollButton(){
		return GuiControll.getRollButton();
	}
	
	public JPanel getRollLayer(){
		return GuiControll.getRollLayer();
	}
	
	public void setCharCoords(int x, int y){
		CharMgr.setCoords(x, y);
	}
	public int getXCharCoord(){
		return CharMgr.getXCoord();
	}
	public int getYCharCoord(){
		return CharMgr.getYCoord();
	}
	public CharacterManager getCharMgr(){
		return CharMgr;
	}
	public Character getPlayer(final int index){
		return CharMgr.getPlayer(index);
	}
	
}

