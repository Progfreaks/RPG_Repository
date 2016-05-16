package valueobject;

import java.util.Random;

/**
 * Klasse fuer einen Wuerfel Objekt.
 * Fuehrt Wuerfel-Prozess aus.
 * @author YOU_HEY
 *
 */
public final class Dice {
	
	static Random random = new Random();

	
		/**
		 * 
		 * Gibt zufaellige Nummer 1~6 zurueck.
		 * @return
		 */
		public static int getDiceNummer(){
			
			int diceNum = random.nextInt(5)+1;
			//Verringert es ein bisschen, dass Augenzahl 1 kriegt.
			if(diceNum == 1) diceNum = random.nextInt(3)+1;
			
			return diceNum;
		}
		
		
	}

	

