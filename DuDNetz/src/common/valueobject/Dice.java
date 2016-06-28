package common.valueobject;

import java.util.Random;

/**
 * Klasse fuer einen Wuerfel Objekt.
 * Fuehrt Wuerfel-Prozess aus.
 * @author YOU_HEY
 *
 */
public final class Dice {

	/**
	 * 
	 * Gibt zufaellige Nummer 1~6 zurueck.
	 * @return
	 */
	public static int getDiceNummer(){
		int diceNum = getRandomNummer(6);
		//Verringert, dass es Augenzahl 1 kriegt.
		if(diceNum == 1) diceNum = getRandomNummer(5);
		if(diceNum == 1) diceNum = getRandomNummer(4);
		return diceNum;
	}
	
	public static int getRandomNummer(int maxNummer){
		Random random = new Random();
		
		return random.nextInt(maxNummer-1)+1;
	}


}



