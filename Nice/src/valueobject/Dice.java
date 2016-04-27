package valueobject;

import java.util.*;

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
	 * Wuerfeln fuer Angreifen
	 * @param ch
	 * @return
	 */
		public static int diceForAtk(final Character ch){
					
		 		
		   int totalDamage = 0;
		
			IO.diceMessage(ch);
		   
	       int diceNum01 = random.nextInt(5)+1;
	       
	       int damage01 = IO.validDiceNumMessage(diceNum01);
	       if(damage01 == 0) return 0;
	       	  
		   IO.diceMessage(ch);
		   
		   int diceNum02 = random.nextInt(5)+1;
		   
		   
		   int damage02 = IO.validDiceNumMessage(diceNum02);
		   if(damage02 == 0) return 0;
		   		    
		   totalDamage 		=  IO.totalDamageMessage(damage01, damage02,ch);			

		   		   
		   		   
		   return totalDamage;
		}
		
		
		
		/**
		 * Wuerfeln fuer MP Aufladen
		 * @param ch
		 * @return
		 */
		public static int diceForMP(final Character ch){
			IO.diceMessage(ch);
		    int diceNum01 = random.nextInt(5)+1;
            int mp01 = IO.showNumMessage(diceNum01);
            
            IO.diceMessage(ch);
		    int diceNum02 = random.nextInt(5)+1;
            int mp02 = IO.showNumMessage(diceNum02);
            
            int totalMP =  IO.chargeMPMessage(mp01,mp02);
            
           
            return totalMP;

		}
		
		/**
		 * Wuerfeln fuer die Bewergung.
		 * @param ch
		 * @param rounds
		 * @return
		 */
		public static int diceForRound(Character ch, int rounds){
			IO.roundDiceMessage(ch, rounds);
			int diceNumRound = random.nextInt(5)+1;
			IO.showNumMessage(diceNumRound);
			return diceNumRound;
		}
	}

	

