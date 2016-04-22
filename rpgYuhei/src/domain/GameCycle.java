package domain;
import gui.Maprender;
import valueobject.character.Character;
import java.io.*;

import valueobject.IO;

import java.util.Random;


public class GameCycle {
	
	Character p01;
	Maprender map;
	private int endIndicator = 0;
	private int rounds;
	
	public void newGame(){
		//setUp();
		map = new Maprender();
		
	}
	public int getRounds(){
		return this.rounds;
	}
    public int getIndicator(){
    	return this.endIndicator;
    }
	
    /**private void setUp(){
		System.out.println("Charakter auswaehlen");
		System.out.println(" 1 : Held \n 2 : Magier \n 3 : Kobold ");

		

		String select = IO.getConsoleInput();
	 
	 switch(select){
     case "1":  p01 = new Warrior("Held",100);
     break;
     case "2": p01 = new Warrior("Magier",100);
     break;
     case "3":  p01 = new Warrior("Kobold",100);
     break;
    
     }
	
	}**/
    public void gameCycle(){
    	
    }
	
	public GameCycle(){
		
		
	}

}
