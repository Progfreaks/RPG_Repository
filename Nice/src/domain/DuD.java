package domain;

import valueobject.events.GameEvent;
import valueobject.fieldobjects.*;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import domain.*;
import valueobject.guiobjects.*;
import valueobject.character.Character;
import domain.GameCycle;
import domain.BattleManager;
import valueobject.character.CharacterEnum;

public class DuD {
	public static DuD game = null;
	private MapHandling BoardMgr = null;
	private GuiController GuiControll = null;
	private CharacterManager CharMgr = null;
	private BattleManager BattleMgr = null;
	private GameCycle cycle = null;
	
	public DuD(){
		this.BoardMgr = new MapHandling(this);
		this.GuiControll = new GuiController(this);
		this.CharMgr = new CharacterManager(this);
		this.BattleMgr = new BattleManager(this);
		this.cycle = new GameCycle(this);
	}
	
		
	

	// verteilt Anfragen an
	// - BoardManager
	// - PlayerManager
	// - GameLogic
	// - ...
	
	public void processEvent(GameEvent ev) {
		ev.process();
	}
	
	public static DuD getGame(){

		return game;
	}
	
	public void setGame(DuD game){
		this.game = game;
	}
	
	public BattleManager getBattleMgr(){
		return this.BattleMgr;
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
	public void renderChest(){
		BoardMgr.renderChest();
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
	
	public JMenuBar getMenuBar(){
		return GuiControll.getMenuBar();
	}
	
	public void setCharCoords(int x, int y){
		CharMgr.setCoords(x, y);
	}
	public int getXCharCoord(){
		return CharMgr.getPlayerXCoord();
	}
	public int getYCharCoord(){
		return CharMgr.getPlayerYCoord();
	}
	public CharacterManager getCharMgr(){
		return CharMgr;
	}
	public Character getPlayer(final int index){
		return CharMgr.getPlayer(index);
	}
	public Character createEnemy(CharacterEnum pCharacter){
		return CharMgr.createCharacter(pCharacter);
	}
	public void nextRound(){
		cycle.nextRound();
	}
	public void setIndicator(boolean in){
		cycle.setIndicator(in);
	}
	
}

