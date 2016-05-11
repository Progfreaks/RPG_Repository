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
	
	public static ArrayList<Character> list = new ArrayList<Character>();
	
	public static void addPlayer(final Character character){
		list.add(character);
	}
	
	public static Character getPlayer(final int index){
		return list.get(index);
	}


}
