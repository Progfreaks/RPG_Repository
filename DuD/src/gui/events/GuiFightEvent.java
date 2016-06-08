package gui.events;

import gui.manager.GuiGameConsole;
import gui.manager.GuiManager;
import gui.manager.GuiMapHandler;
import persistence.character.ICharacterDefs;
import valueobject.Character;
import valueobject.IGameElements;
import valueobject.RoundCount;
import domain.DuD;


public class GuiFightEvent extends GuiGameEvent implements Runnable,IGameElements,ICharacterDefs{

	private DuD game;
	private GuiMapHandler mapHdr;
	private int clickedElement;
	private GuiGameConsole console;
	private int currentPlayerIndex;

	public GuiFightEvent(int x, int y){
		game = DuD.getGame();
		mapHdr = GuiMapHandler.getInstance();
		console = GuiGameConsole.getInstance();
		clickedElement = game.getBoardElement(x, y);
		currentPlayerIndex = game.getCurrentPlayerIndex();
		refreshMapButtonPanel(x, y);
	}
	
	private void refreshMapButtonPanel(int x,int y){
		mapHdr.repaintButton(x, y);
		mapHdr.removeFightActionCall(x, y);
		mapHdr.setMoveActionCall(x, y);
	}
	@Override
	public void process() {
//		new Thread(this).start();
		run();
	}

	@Override
	public void run() {
		Character enemy = getEnemy();
		console.encounterEnemy();
		GuiManager.getInstance().createBattlePanel(enemy);
		startBattle(enemy);
		resetCurrentPlayerIndex();
		GuiManager.getInstance().createMainDungeonPanel();
		console.diceForMove(game.getNextPlayer(),RoundCount.getRoundCount());
	}

	private void resetCurrentPlayerIndex(){
		game.setCurrentPlayerIndex(currentPlayerIndex);
		game.increaseCurrentPlayerIndex();
	}

	private Character getEnemy(){

		Character enemy = null;
		System.out.println("clicked element"+clickedElement);
		if(!game.isAllEnemyDead()){
			switch (clickedElement) {
			case ZOMBIE_ELEMENT:
				enemy = game.getEnemy("Zombie");
				System.out.println("enemy name"+enemy.getName());
				break;
			case GHOST_ELEMENT:
				enemy = game.getEnemy("Ghost");
				break;
			case MUMMY_ELEMENT:
				enemy = game.getEnemy("Mummy");
				System.out.println("enemy name"+enemy.getName());
				break;
			case POT_ELEMENT:
				enemy = game.getEnemy("Pot");
				break;
			case SLEIM_ELEMENT:
				enemy = game.getEnemy("Sleim");
				break;
			default:
				break;
			}
		}else{
			enemy = game.createEnemy(DEMON);
		}
		return enemy;
	}

	private void startBattle(Character enemy){
		Character player = game.getPlayer();
		System.out.println("start battle"+player.getName());
		console.battleMsg(player, enemy);
		while(enemy.isAlive() && !game.isAllPlayerDead()){
			player = game.getNextPlayer();
			console.battleMsg(player, enemy);
		}
	}

	

}
