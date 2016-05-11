package domain;

import valueobject.events.GameEvent;
import valueobject.fieldobjects.*;
import javax.swing.*;
import domain.*;
import gui.guiobjects.*;
import valueobject.PlayerArray;
import valueobject.character.Character;
import gui.CommandoInput;
import gui.GuiManager;
import persistence.character.CharacterData;
import java.awt.image.BufferedImage;

public class DuD {
	public static DuD game = null;
	private MapHandling BoardMgr = null;
	private GuiController GuiControll = null;
	private CharacterManager CharMgr = null;
	private BattleManager BattleMgr = null;
	private GameCycle cycle = null;
	private CommandoInput console = null;
	private CentralSave save = null;
	public DuD(){
		this.BoardMgr = new MapHandling(this);
		this.GuiControll = new GuiController(this);
		this.CharMgr = new CharacterManager(this);
		this.BattleMgr = new BattleManager(this);
		this.cycle = new GameCycle(this);
		this.save = new CentralSave();
		
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
	
	public void removePanel(int i){
		GuiControll.removePanel(i);
	}
	
	public void addPanel(Object obj, int i, String in){
		GuiControll.addPanel(obj, i, in);
	}
	public void refreshGUI(){
		GuiControll.refresh();
	}
	public void fillLayer(int index, int i, int s){
		GuiControll.fillLayer(index, i, s);
	}
	public void setGuiMgr(GuiManager mgr){
		GuiControll.setGuiMgr(mgr);
	}
	public BackLayer getBackLayer(){
		return GuiControll.getBackLayer();
	}
	public void renderGUI(){

		GuiControll.renderGUI();
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
	public Character createEnemy(CharacterData pCharacter){
		return CharMgr.createCharacter(pCharacter);
	}
	public void nextRound(){
		cycle.nextRound();
	}
	public void setIndicator(boolean in){
		cycle.setIndicator(in);
	}
	public BufferedImage loadImg(String path){
		return save.loadImg(path);
	}
	
	public void newGame(){
		Character player = null;
		
		
		//CharacterManager erzeugt einen Speiler
		CharacterManager cm = game.getCharMgr();
		System.out.println("moin");
		boolean check = true;

		do {

			try {
				player = cm.createCharacter(CentralSave.console.selectCharacter());
				check = false;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.print("bitte nur eine Zahl eingeben\n\n");
			}

		} while (check);
		
		
		PlayerArray.addPlayer(player);//Der ausgewaehlte Player ist ins Array hinzugefuegt.
	    renderMap();
		
		
	
	}

	
}

