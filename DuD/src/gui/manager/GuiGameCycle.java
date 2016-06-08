package gui.manager;

import gui.events.GuiFightEvent;
import gui.events.GuiGameEvent;
import gui.events.GuiMoveEvent;
import gui.events.GuiPickUpEvent;
import gui.events.GuiRollEvent;
import gui.events.GuiEndGameEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import valueobject.Character;
import valueobject.RoundCount;
import domain.DuD;

public class GuiGameCycle {

	public  enum PHASE  { DEFAULT,MOVE,FIGHT,PICK,ROLL }
	private PHASE phase;
	private DuD game;
	private GuiGameConsole console;
	public static GuiGameCycle gameCycle;
	private Object key;
	private int clickedCoordinateX;
	private int clickedCoordinateY; 

	private GuiGameCycle(){
		game = DuD.getGame();
		console = GuiGameConsole.getInstance();
		key = new Object();
	}


	public static GuiGameCycle getInstance(){
		if(gameCycle == null) gameCycle = new GuiGameCycle();
		return gameCycle;
	}

	
	public void startGame(){
		
		boolean gameComplete = false;
		while(!gameComplete){
			if(phase == PHASE.DEFAULT){
				console.diceForMove(game.getPlayer(),RoundCount.getRoundCount());
				
			}
			if (phase == PHASE.MOVE) {
				GuiMoveEvent moveEvent = new GuiMoveEvent(clickedCoordinateX, clickedCoordinateY);
				moveEvent.process();
			}
			if (phase == PHASE.FIGHT) {
				GuiGameEvent fightEvent = new GuiFightEvent(clickedCoordinateX, clickedCoordinateY);
				fightEvent.process();
			}
			if (phase == PHASE.PICK) {
				GuiGameEvent pickEvent = new GuiPickUpEvent(clickedCoordinateX, clickedCoordinateY);
				pickEvent.process();
			}
			if (phase == PHASE.ROLL) {
				GuiGameEvent rollEvent = new GuiRollEvent();
				rollEvent.process();
			}

			
			if(phase != PHASE.DEFAULT && phase != PHASE.ROLL && phase != PHASE.FIGHT){
				console.diceForMove(game.getNextPlayer(),RoundCount.getRoundCount() );
			}
			
			synchronized (key) {
				try {
					key.wait();
				} catch (InterruptedException e) {
					System.out.println("ex bei startGame in GameCycle");
				}	
			}
		}
	}


	public void wake(){
		synchronized (key) {
			key.notify();
		}
	}

	public void setPhase(PHASE phase){
		this.phase = phase;
	}


	public void setClickedCoordinate(ActionEvent e){
		JButton[][] buttonMatrix =  GuiMapHandler.getInstance().getMapButtonPanel().getMapButtonMatrix();
		JButton clickedButton = (JButton)e.getSource();
		for (int i = 0; i < buttonMatrix.length; i++) {
			for (int j = 0; j < buttonMatrix[i].length; j++) {
				if (buttonMatrix[i][j] == clickedButton) {
					
					clickedCoordinateX = j;
					clickedCoordinateY = i;
				}
			}
		}
	}
	
	private void checkResult(Character enemy){
		if(!enemy.isAlive()){
		}else if(game.isAllPlayerDead()){
			//game over TODO
			console.append("game over...");
		}
		if(game.isAllEnemyDead() && game.checkAllLever()){
			GuiGameEvent endStage = new GuiEndGameEvent();//end gegner platieren
			
		}
	}

}
