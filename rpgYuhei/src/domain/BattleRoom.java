package domain;

import java.util.Vector;

import valueobject.*;
import valueobject.character.Character;




public class BattleRoom {
	
	private Vector<Character> players;
	private Vector<Character> enemys;
	private Character p01 ;
	private Character e01;
	

	
	
	public BattleRoom(){
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
	
	
	public void startBattle(){
		
		
		p01 = players.get(0);
	    e01 = enemys.get(0);
	    IO.roundMessage(p01, e01);

		
	}
}
