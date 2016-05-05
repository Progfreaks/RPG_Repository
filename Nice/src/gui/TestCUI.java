package gui;

import domain.BattleManager;
import domain.CentralSave;
import domain.CharacterManager;
import domain.DuD;
import domain.exceptions.InvalidNumberException;
import persistence.character.CharacterDataMap;
import valueobject.PlayerArray;
import valueobject.character.Character;
import valueobject.character.*;
import valueobject.events.StartEvent;


public class TestCUI {

	static Character player = null;
	static Character enemy = null;
	static CharacterManager cm;
	
	private static CharacterDataMap map = CharacterDataMap.getInstance();



	public static void main(String[] args) {
		
		

		CommandoInput gui = new CommandoInput("Kommando Eingabe");
		
		CentralSave.setConsole(gui);
		
		DuD game = new DuD();
		game.setGame(game);
	

		
		CharacterManager cm = new CharacterManager(game);
		player = cm.createCharacter(gui.selectCharacter());
		
		PlayerArray.addPlayer(player);// Der ausgewaehlte Player ist ins Array hinzugefuegt.
		
		player = PlayerArray.getPlayer(0);
		enemy = cm.createCharacter(map.getCharacterData(6));

		BattleManager bm = new BattleManager(new DuD());

		bm.addPlayer(player);
		bm.addEnemy(enemy);

		bm.startBattle();
		

		 
//		DuD game = new DuD();
//		game.setGame(game);
//
//		
//		CharacterManager cm = new CharacterManager(game);
//		
//		boolean check = true;
//		
//		
//		do {
//			
//			try {
//				
//				
//				player = cm.createCharacter(IO.chooseCharacterMessage());
//				check = false;
//			}catch (NumberFormatException e) {
//				e.printStackTrace();
//				System.out.print("bitte nur eine Zahl eingeben\n\n");
//			}catch (InvalidNumberException e) {
//				e.printStackTrace();
//			}
//			
//		} while (check);
//		
//		
//
//		PlayerArray.addPlayer(player);// Der ausgewaehlte Player ist ins Array hinzugefuegt.
//										
//		player = PlayerArray.getPlayer(0);
//		enemy = cm.createCharacter(map.getCharacterData(6));
//
//		BattleManager bm = new BattleManager(new DuD());
//
//		bm.addPlayer(player);
//		bm.addEnemy(enemy);
//
//		bm.startBattle();



      
	}
	

}



