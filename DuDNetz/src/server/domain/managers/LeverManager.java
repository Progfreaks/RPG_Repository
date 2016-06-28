package server.domain.managers;

import server.persistence.gamastate.GameState;

public class LeverManager {
	
	private int usedLever;
	
	public LeverManager(){
		usedLever = 0;
	}
	public void countUpLever(){
		usedLever++;
	}
	
	//Ausgabe Methode f�r die Konsole
	public String getCountPulledLever(){
		String msg = "Anzahl der benutzten Schalter: " + usedLever + "/2";
		return msg;
	}
	public boolean isAllLeverPulled(){
		if(usedLever == 2){
			return true;
		}
		return false;
	}
	//Save Methode f�r GameState
	public void saveCurrentLeverCount(){
		
		GameState.getInstance().setLeverCount(usedLever);
	}

}
