package valueobject;

import java.util.*;

import domain.CentralSave;
import valueobject.character.Character;

/**
 * Klasse fuer einen Wuerfel Objekt.
 * Fuehrt Wuerfel-Prozess aus.
 * @author YOU_HEY
 *
 */
public final class Dice {
	
	static Random random = new Random();

	/**
	 * Wuerfeln fuer Angreifen.
	 * @param ch
	 * @return
	 */
		public static int diceForAtk(final Character ch){
					
		 		
		   int totalDamage = 0;
		
			CentralSave.console.diceMessage(ch);
		   
	       int diceNum01 = getDiceNummer();
	       
	       
	       int damage01 = CentralSave.console.validDiceNumMessage(diceNum01);
	       if(damage01 == 0) return 0;
	       	  
		   CentralSave.console.diceMessage(ch);
		   
		   int diceNum02 = getDiceNummer();
		   
		   
		   int damage02 = CentralSave.console.validDiceNumMessage(diceNum02);
		   if(damage02 == 0) return 0;
		   		    
		   totalDamage 		=  CentralSave.console.totalDamageMessage(damage01, damage02,ch);			

		   		   
		   		   
		   return totalDamage;
		}
		
		
		
		
		
		/**
		 * Wuerfeln fuer MP Aufladen
		 * @param ch
		 * @return
		 */
		public static int diceForMP(final Character ch){
			
			CentralSave.console.diceMessage(ch);
		    int diceNum01 = getDiceNummer();
            int mp01 = CentralSave.console.showNumMessage(diceNum01);
            
            CentralSave.console.diceMessage(ch);
		    int diceNum02 = getDiceNummer();
            int mp02 = CentralSave.console.showNumMessage(diceNum02);
            
            int totalMP =  CentralSave.console.chargeMPMessage(mp01,mp02);
            
           
            return totalMP;

		}
		
		/**
		 * Hiffs Methode fuer diceForAtk und diceForMP
		 * Gibt zufaellige Nummer 1~6 zurueck.
		 * @return
		 */
		public static int getDiceNummer(){
			
			int diceNum = random.nextInt(5)+1;
			//Verringert ein bisschen dass Augenzahl 1 kriegt.
			if(diceNum == 1) diceNum = random.nextInt(3)+1;
			
			return diceNum;
		}
		
		/**
		 * Wuerfeln fuer die Bewergung.
		 * @param ch
		 * @param rounds
		 * @return
		 */
		public static int diceForRound(Character ch, int rounds){
			CentralSave.console.roundDiceMessage(ch, rounds);
			int diceNumRound = random.nextInt(5)+1;
			CentralSave.console.showNumMessage(diceNumRound);
			return diceNumRound;
		}
	}

	

