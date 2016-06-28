package client.gui.events;

import client.gui.manager.GuiGameConsole;
import client.gui.manager.GuiMapHandler;
import client.net.Facade;
import common.valueobject.Character;
import common.valueobject.ICharacterDefs;
import common.valueobject.IGameElements;
import server.domain.DuD;


public class GuiFightEvent   implements Runnable,IGameElements,ICharacterDefs,GuiGameEvent{

	private DuD game;
	private GuiMapHandler mapHdr;
	private int clickedElement;
	private GuiGameConsole console;
	private int currentPlayerIndex;
	private Facade single;

	public GuiFightEvent(int clickedCoordinateX, int clickedCoordinateY,Facade single){
		game = DuD.getGame();
		mapHdr = GuiMapHandler.getInstance();
		console = GuiGameConsole.getInstance();
		this.single = single;
		console.setSingle(this.single);
		clickedElement = game.getBoardElement(clickedCoordinateX, clickedCoordinateY);
		currentPlayerIndex = game.getCurrentPlayerIndex();
		refreshMapButtonPanel(clickedCoordinateX, clickedCoordinateY);
	}
	
	
	
	private void refreshMapButtonPanel(int x,int y){
		mapHdr.repaintButton(x, y);
		mapHdr.removeFightActionCall(x, y);
		mapHdr.setMoveActionCall(x, y);
	}
	@Override
	public void process() {
//		// enemy = getEnemy();
//		console.encounterEnemy();
//		GuiManager.getInstance().createBattlePanel(enemy);
//		
//		run();
	}
	
//	public void  goBattle(Character enemy){
//		startBattle(enemy);
//		checkResult(enemy);
//		resetCurrentPlayerIndex();
//		GuiManager.getInstance().createMainDungeonPanel();
//	}

	Character enemy;
	public void run() {
//		Character enemy = getEnemy();
//		console.encounterEnemy();
//		GuiManager.getInstance().createBattlePanel(enemy);
//		startBattle(enemy);
//		checkResult(enemy);
//		resetCurrentPlayerIndex();
//		GuiManager.getInstance().createMainDungeonPanel();
		//console.diceForMove(game.getNextPlayer(),RoundCount.getRoundCount());
	}

//	private void resetCurrentPlayerIndex(){
//		game.setCurrentPlayerIndex(currentPlayerIndex);
//		game.increaseCurrentPlayerIndex();
//	}

	public Character getEnemy(){

		Character enemy = null;
		System.out.println("clicked element"+clickedElement);
		if(!single.isAllEnemyDead()){
			switch (clickedElement) {
			case ZOMBIE_ELEMENT:
				enemy = single.getEnemy("Zombie");
				System.out.println("Enemy created ((FightEvent)) "+enemy.getName()+" life; "+enemy.getLife()+"\n");
				break;
			case GHOST_ELEMENT:
				enemy = single.getEnemy("Ghost");
				break;
			case MUMMY_ELEMENT:
				enemy = single.getEnemy("Mummy");
				System.out.println("enemy name"+enemy.getName());
				break;
			case POT_ELEMENT:
				enemy = single.getEnemy("Pot");
				break;
			case SLEIM_ELEMENT:
				enemy = single.getEnemy("Sleim");
				break;
			default:
				break;
			}
		}else{
			enemy = game.createEnemy(DEMON);
		}
		enemy.setMaxLife(enemy.getLife());
		return enemy;
	}
	
//	private void startBattle(Character enemy){
//		Character player = single.getPlayer();
//		System.out.println("start battle"+player.getName());
//		
//		
//		console.battleMsg(player, enemy);
//		while(enemy.isAlive() && !single.isAllPlayerDead()){
//			player = single.getNextPlayer();
//			console.battleMsg(player, enemy);
//		}
//	}

//	private void startBattle(Character enemy){
//		Character player = game.getPlayer();
//		System.out.println("start battle"+player.getName());
//		console.battleMsg(player, enemy);
//		while(enemy.isAlive() && !game.isAllPlayerDead()){
//			player = game.getNextPlayer();
//			console.battleMsg(player, enemy);
//		}
//	}

	private void checkResult(Character enemy){
		if(!enemy.isAlive()){
			
		}else if(game.isAllPlayerDead()){
			//game over TODO
			console.append("game over...");
		}
		
		checkMission();
	}
	
	private void checkMission(){
		if(game.isAllEnemyDead()&&game.isAllLevePulled()){
			mapHdr.placeBoss();//end gegner platieren
			console.appendln("Endgegner ist aufgetreten!!!!!!\n");
		}
	}

}
