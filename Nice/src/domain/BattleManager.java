package domain;

import java.util.List;
import java.util.Vector;

import domain.DuD;
import valueobject.*;
import valueobject.character.Character;



/**
 * Klasse fuer die Verwaltung des Kampfs.
 * @author YOU_HEY
 *
 */
public class BattleManager {
	
	
	private DuD game;

	    //Speichert alle Spieler.
		private List<Character> players;
		//Speichert alle Gegner.
		private List<Character> enemys;
		
		private Character p01 ;
		private Character e01;
	
	
	public BattleManager(DuD game){
		this.game = game;
		players = new Vector<Character>();
		enemys = new Vector<Character>();
	}
	
	/**
	 * Fuegt einen Spieler ins Array hinzu.
	 * @param player
	 * @return
	 */
	public boolean addPlayer(final Character player){
		return players.add(player);
	}

	/**
	 * Fuegt einen Gegner ins Array hinzu.
	 * @param enemy
	 * @return
	 */
	public boolean addEnemy(final Character enemy){
		return enemys.add(enemy);
	}
	
	/**
	 * Gibt die Groesse des PlayerList zurueck.
	 * @return
	 */
	public int numberOfPlayer(){
		return players.size();
	}
	
	/**
	 * Gibt die Groesse des GegnerList zurueck.
	 * @return
	 */
	public int numberOfEnemy(){
		return enemys.size();
	}
	
	
	/**
	 * Startet den Kampf.
	 */
	public void startBattle(){
		
		
		p01 = players.get(numberOfPlayer()-1);
	    e01 = enemys.get(numberOfEnemy()-1);
	    IO.roundMessage(p01, e01);

		
	}
}
