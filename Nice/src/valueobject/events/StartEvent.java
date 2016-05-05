package valueobject.events;

import domain.CentralSave;
import domain.CharacterManager;
import valueobject.character.Character;
import valueobject.PlayerArray;
import domain.DuD;
import domain.exceptions.InvalidNumberException;

/**
 * Klasse fuer die Konfiguration vom Spiel-Angfang
 * 
 * @author YOU_HEY
 *
 */
public class StartEvent extends GameEvent {
	private DuD game = null;

	@Override
	public void process() {
		game = DuD.getGame();

		Character player = null;

		// CharacterManager erzeugt einen Speiler
		CharacterManager cm = game.getCharMgr();

		boolean check = true;

		do {

			try {
				player = cm.createCharacter(CentralSave.console.selectCharacter());
				check = false;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.print("bitte nur eine Zahl eingeben\n\n");
			}

		} while (check);

		PlayerArray.addPlayer(player);// Der ausgewaehlte Player ist ins Array
										// hinzugefuegt.
		game.renderMap();

	}

}
