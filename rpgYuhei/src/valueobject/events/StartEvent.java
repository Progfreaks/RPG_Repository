package valueobject.events;

import domain.CharacterManager;
import valueobject.character.Character;
import valueobject.IO;
import valueobject.PlayerArray;


/**
 * Klasse fuer die Konfiguration vom Spiel-Angfang
 * @author YOU_HEY
 *
 */
public class StartEvent extends GameEvent{

	@Override
	public void process() {
		
		Character player = null;
		
		
		//CharacterManager erzeugt einen Speiler
		CharacterManager cm = new CharacterManager();
		
		player = cm.createCharacter(IO.chooseCharacterMessage());
		
		
		PlayerArray.addPlayer(player);//Der ausgewaehlte Player ist ins Array hinzugefuegt.
		
	}

}
