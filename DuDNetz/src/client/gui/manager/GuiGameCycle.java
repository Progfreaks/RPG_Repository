package client.gui.manager;

import client.gui.events.GuiCureEvent;
import client.gui.events.GuiFightEvent;
import client.gui.events.GuiGameEvent;
import client.gui.events.GuiLeverEvent;
import client.gui.events.GuiMoveEvent;
import client.gui.events.GuiRollEvent;
import client.net.Facade;
import common.valueobject.Character;
import server.domain.DuD;

public class GuiGameCycle {

	public  enum PHASE  { DEFAULT,MOVE,FIGHT,FIGHT2,FIGHT3,LEVER,ROLL,CURE,NEXT,ROUND }
	volatile private  PHASE phase;
	private DuD game;
	private Facade facade;
	private GuiGameConsole console;
	public static GuiGameCycle gameCycle;
	private Object key;
	private int clickedCoordinateX;
	private int clickedCoordinateY; 
	private Facade single;

	public GuiGameCycle(){
		game = DuD.getGame();
		console = GuiGameConsole.getInstance();
		key = new Object();
	}


	public static GuiGameCycle getInstance(){
		if(gameCycle == null) gameCycle = new GuiGameCycle();
		return gameCycle;
	}

	public void setFacade(Facade facade){
		this.facade = facade;
	}
	
	public void setFacadeSingle(Facade facade){
		this.single = facade;
	}
	
	public PHASE getPhase(){
		return phase;
	}

	public void startGame(){
	}
	
	private void sendNextPhase(){
		facade.sendPhase("NEXT");
		console.blankTextArea();
		console.append("Runde von anderem Player...");
	}
	
	
	boolean roolled = false;
	Character enemy;
	public void takeAction(){
		if(getPhase() == PHASE.DEFAULT){
			System.out.println("executed DEFAULT(nothing happens) event by ((GameCycle)) \n");
		}
		if (getPhase() == PHASE.ROLL) {
			GuiGameEvent rollEvent = new GuiRollEvent();
			rollEvent.process();
			roolled = true;
		}
		if (getPhase() == PHASE.MOVE) {
			System.out.println("executed MOVE event by ((GameCycle)) ");
			GuiMoveEvent moveEvent = new GuiMoveEvent(clickedCoordinateX, clickedCoordinateY);
			moveEvent.process();
			phase =PHASE.DEFAULT;
			if(roolled){
sendNextPhase();
roolled = false;
			}
		}
		if(getPhase() == PHASE.FIGHT){
			System.out.println("executed FIGHT event by ((GameCycle)) ");
			GuiFightEvent fightEvent = new GuiFightEvent(clickedCoordinateX, clickedCoordinateY,single);
			 enemy = fightEvent.getEnemy();
			console.encounterEnemy();
			GuiManager.getInstance().createBattlePanel(enemy);
			facade.sendPhase("FIGHT2");
			
		}
		
		if(getPhase() == PHASE.FIGHT2){
			System.out.println("executed FIGHT(2) event by ((GameCycle)) ");

			//Character player = single.getPlayer();
			Character player = single.getPlayerByIndex(single.getMyNumber());
			System.out.println("start battle"+player.getName());
			console.battleMsg(player, enemy);
			if((enemy.isAlive() && !single.isAllPlayerDead())){
				facade.sendPhase("FIGHT3");
			}else{
				facade.sendPanelName("Main");
			}
		}
		if(getPhase() == PHASE.FIGHT3){
			System.out.println("executed FIGHT(2) event by ((GameCycle)) ");
			int maxLife = enemy.getMaxLife();
			enemy = single.getEnemy(enemy.getName());
			enemy.setMaxLife(maxLife);
			Character player = single.getPlayerByIndex(single.getMyNumber());
			System.out.println("start battle"+player.getName());
			console.battleMsg(player, enemy);
			if((enemy.isAlive() && !single.isAllPlayerDead())){
				facade.sendPhase("FIGHT3");
			}else{
				facade.sendPanelName("Main");
			}
//			
		}
		
	

		
		if(getPhase() == PHASE.CURE){
			if(roolled){
			System.out.println("executed CURE event by ((GameCycle)) ");
			GuiGameEvent curetEvent = new GuiCureEvent(clickedCoordinateX, clickedCoordinateY,single);
			curetEvent.process();
			
			if(roolled){
				sendNextPhase();
				roolled = false;
			}
			}else{
				System.out.println("executed CURE -> MOVE event by ((GameCycle)) ");
				GuiMoveEvent moveEvent = new GuiMoveEvent(clickedCoordinateX, clickedCoordinateY);
				moveEvent.process();
			}
			
		}
		if(getPhase() == PHASE.LEVER){
			
			if(roolled){
			System.out.println("executed LEVER event by ((GameCycle)) ");
			GuiGameEvent leverEvent = new GuiLeverEvent(clickedCoordinateX, clickedCoordinateY,single);
			leverEvent.process();
			
			if(roolled){
				sendNextPhase();
				roolled = false;
			}
			}else{
				System.out.println("executed CURE -> MOVE event by ((GameCycle)) ");
				GuiMoveEvent moveEvent = new GuiMoveEvent(clickedCoordinateX, clickedCoordinateY);
				moveEvent.process();
			}
			
		}
		if (getPhase() == PHASE.ROUND) {
			System.out.println("executed ROUND event by ((GameCycle)) \n");
			//String name = single.getPlayerName();
			String name = GuiManager.ClientName;
			console.diceForMove(name);//,RoundCount.getRoundCount());
		}
	}

	public void wake(){
		System.out.println("Waked ((GameCycle))-> "+phase+"\n");
		synchronized (key) {
			key.notify();
		}
	}

	public void setPhase(PHASE phase){
		this.phase = phase;
		System.out.println("phase is set by ((GameCycle))-> "+phase+"\n");

	}


	public void setClickedCoordinate(int[] coords){
		clickedCoordinateX = coords[0];
		clickedCoordinateY = coords[1];
	}

	public void setClickedXCoordinate(int x){
		clickedCoordinateX = x;
	}
	public void setClickedYCoordinate(int y){
		clickedCoordinateY = y;
	}

}
