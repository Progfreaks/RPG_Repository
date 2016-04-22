package domain;

import java.util.Vector;

import valueobject.*;
import valueobject.character.Character;



/**
 * Klasse fuer die Verwaltung des Kampfs.
 * @author YOU_HEY
 *
 */
public class BattleManager {
	
	private Vector<Character> players;
	private Vector<Character> enemys;
	private Character p01 ;
	private Character e01;
	

	
	
	public BattleManager(){
		players = new Vector<Character>();
		enemys = new Vector<Character>();
	}
	
	public boolean addPlayer(final Character player){
		return players.add(player);
	}

	public boolean addEnemy(final Character enemy){
		return enemys.add(enemy);
	}
	
	public int numberOfPlayer(){
		return players.size();
	}
	
	public int numberOfEnemy(){
		return enemys.size();
	}
	
	
	/**
	 * Startet den Kampf.
	 */
	public void startBattle(){
		
		
		p01 = players.get(0);
	    e01 = enemys.get(0);
	    IO.roundMessage(p01, e01);

		
	}
}
