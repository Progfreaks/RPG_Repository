package valueobject;


import java.util.*;
import valueobject.character.Character;


/**
 * Hier werden alle Spieler gespeichert.
 * @author YOU_HEY
 *
 */
public final class PlayerArray {
	
	
	
	private static Character player01;
	private static Character player02;
	private static Character player03;
	private static Character player04;
	
	//List, die alle Speilern enthaelt.
	public static List<Character> list = new ArrayList<Character>();
	
	/**
	 * Fuegt einen Speieler in die List hinzu.
	 * @param character
	 */
	public static void addPlayer(final Character character){
		list.add(character);
	}
	
	/**
	 * Bekommt einen Spieler aus der Liste.
	 * @param index
	 * @return
	 */
	public static Character getPlayer(final int index){
		return list.get(index);
	}


}
