package parsistence.character;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class CharacterData implements Serializable, ICharacterDefs {

	
	

	private static final long serialVersionUID = 7935540778515823936L;

	

	private String name;//charakter name

	private int hp;//character hp

	private int mp;//character mp

	private boolean isPlayer;//ob der Charalkter ein Spieler ist
	
	//Speichert Chatakter-ID als Integer
	private int characterId;
	
	/**
	 * Konstruktor. Bekommt Status als Paramerter uebergeben.
	 * @param pCharacterId
	 */
	public CharacterData(final int pCharacterId){
		characterId = pCharacterId;
		name  = "***";//default
		hp = 0;//default
		mp = 0;//default
		isPlayer = true;//default
	}
	
	
	
	/**
	 * Bekommt entsprechenden Charakter-Wert.
	 * @param index
	 * @return
	 */
	public String getValue(int index){
		
		String value = "";
		
		switch (index) {
		case 1:
			value = getName();
			break;
		case 2:
			value = String.valueOf(getHP());
			break;
		case 3:
			value = String.valueOf(getMP());
			break;
		case 4:
			value = String.valueOf(getIsPlayer());

			break;

		default:
			
			break;
		}
		return value;
		
	}

	
	/**
	 * Setzt entsprechenden Charakter-Wert.
	 * @param index
	 * @param pValue
	 */
	public void setValue(int index, String pValue) {
		switch (index) {
		case 1:
			setName(pValue);
			break;
		case 2:
			setHP(Integer.parseInt(pValue));
			break;
		case 3:
			setMP(Integer.parseInt(pValue));
			break;
		case 4:
			serIsPlayer(Boolean.parseBoolean(pValue));
			break;

		default:
			break;
		}
	}
	
	//----------ganze Getter und Setter Methode-----------------
	
	private String getName() {
		return name;
	}

	private int getHP() {
		return hp;
	}

	private int getMP() {
		return mp;
	}

	private boolean getIsPlayer() {
		return isPlayer;
	}
	
	private void setName(String pName) {
		name = pName;
	}

	private void setHP(int pHP) {
		hp = pHP;
	}

	private void setMP(int pMP) {
		mp = pMP;
	}

	private void serIsPlayer(boolean pIsPlayer) {
		isPlayer = pIsPlayer;
	}
	
	
	@Override
	public String toString(){
		return CHARACTER_NAME[characterId];
	}
}
