package gui.events;

import domain.DuD;
import persistence.character.CharacterDataMap;
import persistence.character.CharacterData;
import valueobject.character.Character;

public class CharSelectEvent extends GameEvent {
	private static CharacterDataMap map = CharacterDataMap.getInstance();
	private CharacterData status;
	private Character newP;
	private static DuD game;
	private int pos;
	public void run() {
		process();
		
	}
	public void process(){
		game = DuD.getGame();
		status = map.getCharacterData(pos);
		newP = game.createPlayer(status);
		game.addPlayer(newP);
		System.out.println(status.getValue(status.NAME) + "has been added to the game!");
		
	}
	public CharSelectEvent(int pos){
		this.pos = pos;
	}
}
