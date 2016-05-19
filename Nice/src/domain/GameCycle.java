package domain;
import valueobject.character.Character;
import gui.GuiGameConsole;

import java.io.*;

import domain.DuD;
import valueobject.Dice;





import java.util.Random;


public class GameCycle {
	
	private Character player;
	private boolean endIndicator;
	private static int rounds;
	private Dice playDice;
	private static DuD game;
	private GuiGameConsole console;
	
	
	public GameCycle(DuD game){
		
		this.endIndicator = false;
		this.rounds = 1;
		playDice = new Dice();
		this.game = game;
		console = GuiGameConsole.getInstance();
		
	}
	

	public static int getRounds(){
		return rounds;
	}
    public boolean getIndicator(){
    	return this.endIndicator;
    }
    public void setIndicator(boolean in){
    	
    }
	
    public int nextRound(){
		
    	int diceNum = 0;
    	
    	if(!endIndicator){
    		
    		diceNum = 0;
    		player = game.getPlayer(0);
    		
    		
    		diceNum = console.diceForRound(player, rounds);
    		rounds++;
    		
    	}
    	
    	else{
    		
    		player = game.getPlayer(0);
    		 diceNum = 0;
    		diceNum = console.diceForRound(player, rounds);
    		rounds++;
    	}
    	
    	return diceNum;
    }
    	
    	
    
	
	

}
