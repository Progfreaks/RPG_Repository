package valueobject;


import java.util.*;
import valueobject.character.Character;


/**
 * Hier werden alle Spieler gespeichert.
 * @author YOU_HEY
 *
 */
public final class EnemyArray {
	
	
	
	private static Character enemy01;
	private static Character enemy02;
	private static Character enemy03;
	private static Character enemy04;
	
	public static ArrayList<Character> list = new ArrayList<Character>();
	
	public static void addPlayer(final Character character){
		list.add(character);
	}
	
	public static Character getPlayer(final int index){
		return list.get(index);
	}


}
