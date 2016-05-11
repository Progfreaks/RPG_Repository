package valueobject.events;

import domain.CharacterManager;
import domain.CentralSave;
import valueobject.character.Character;
import valueobject.PlayerArray;
import domain.DuD;
import domain.exceptions.InvalidNumberException;
import gui.CommandoInput;


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
	    game.removePanel(0);
	    
	    game.renderGUI();
	}

}
