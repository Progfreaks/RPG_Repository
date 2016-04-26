package valueobject.events;

import domain.CharacterManager;
import valueobject.character.Character;
import valueobject.IO;
import valueobject.PlayerArray;
import domain.DuD;


/**
 * Klasse fuer die Konfiguration vom Spiel-Angfang
 * @author YOU_HEY
 *
 */
public class StartEvent extends GameEvent{
	private DuD game = null;

	@Override
	public void process() {
		
	    game = DuD.getGame();
	    
		Character player = null;
		
		
		CharacterManager cm = game.getCharMgr();
		
		
		//CharacterManager erzeugt einen Speiler
		player = cm.createCharacter(IO.chooseCharacterMessage());
		
		
		PlayerArray.addPlayer(player);//Der ausgewaehlte Player ist ins Array hinzugefuegt.
		
	    game.renderMap();
		
		
	}

}
