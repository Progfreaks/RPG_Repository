package valueobject.events;

import valueobject.character.Character;
import valueobject.IO;
import valueobject.PlayerArray;
import valueobject.character.Warrior;


/**
 * Klasse fuer die Konfiguration vom Spiel-Angfang
 * @author YOU_HEY
 *
 */
public class StartEvent extends GameEvent{

	@Override
	public void process() {
		
		Character character = null;
		
		switch(IO.chooseCharacterMessage()){
		case "1": character = new Warrior("Held",100) ; character.setIsPlayer(true); break;
		case "2": character = new Warrior("Magier",100) ; character.setIsPlayer(true); break;
		case "3": character = new Warrior("Kobold",100) ; character.setIsPlayer(true); break;

		}
		
		PlayerArray.addPlayer(character);//Der ausgewaehlte Player ist ins Array hinzugefuegt.
		
	}

}
