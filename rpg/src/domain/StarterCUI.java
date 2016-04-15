package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

import valueobject.ICharacter;
import valueobject.Warrior;
import valueobject.Zombie;



public class StarterCUI {

	static ICharacter p01 = null;
	/**
	private static void setUp(){
		System.out.println("Charakter auswaehlen");
		System.out.println(" 1 : Held \n 2 : Magier \n 3 : Kobold ");

		

		BufferedReader in;
        String select = null;
		in = new BufferedReader(new InputStreamReader(System.in));
		try {
			select = in.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	 
	 switch(select){
     case "1":  p01 = new Warrior("Held",100);
     break;
     case "2": p01 = new Warrior("Magier",100);
     break;
     case "3":  p01 = new Warrior("Kobold",100);
     break;
    
     }
	
	}
	*/
	

	public static void main(String[] args) {

		
		//ICharacter p01 = null;
		
		
       
		p01 = new Warrior("Held", 100);
		Zombie z01 = new Zombie("Zombie", 40);
        p01.setIsPlayer(true);		
		  

        z01.setIsPlayer(false);		

		
        //p01.attack();
		BattleRoom battleRoom = new BattleRoom();
		
		battleRoom.addPlayer(p01);
		battleRoom.addEnemy(z01);
		battleRoom.startBattle();


      
	}
	

}



