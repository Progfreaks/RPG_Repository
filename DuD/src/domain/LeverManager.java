package domain;

/**
 * Verwaltet die durch das GuiPickUpEvent erzeugten Ereignisse
 * @author T
 *
 */
import persistence.gamestate.GameState;

public class LeverManager {
	
	private int usedLever;
	public LeverManager(){
		usedLever = 0;
	}
	public void leverPulled(){
		usedLever++;
	}
	
	//Ausgabe Methode für die Konsole
	public String pulledLever(){
		String msg = "Anzahl der benutzten Schalter: " + usedLever + "/2";
		return msg;
	}
	public boolean checkAllLever(){
		if(usedLever == 2){
			return true;
		}
		return false;
	}
	//Save Methode für GameState
	public void saveCurrentLeverCount(){
		
		GameState.getInstance().setLeverCount(usedLever);
	}

}
