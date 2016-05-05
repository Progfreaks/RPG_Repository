package persistence.character;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;



/**
 * Diese Klasse repraesentiert Charaker Detei. Es werden alle Charakerteigenschaften gespeichert.
 * @author YOU_HEY
 *
 */
public class CharacterData implements Serializable, ICharacterDefs {

	
	
	/**
	 * Inner Klasse.Diese repraesentiert Charakterfaehigkeiten.
	 * @author YOU_HEY
	 *
	 */
	public class Skill implements Serializable{
		
		String name;
		int mp;
		double damage;
		
		public Skill(String pName,int pMP,double pDamage){
			name = pName;
			mp = pMP;
			damage = pDamage;
		}
		
		public void setName(String param){
			name = param;
			
		}
		
		public void setMP(int param){
			mp = param;
			
		}
		
		public void setDamage(double param){
			damage = param;
			
		}
		
		public String getName(){
			return name;
		}
		
		public int getMP(){
			return mp;
		}
		
		public double getDamage(){
			return damage;
		}
	}
	

	private static final long serialVersionUID = 7935540778515823936L;

	

	private String name;//charakter name

	private int hp;//character hp

	private int mp;//character mp

	private boolean isPlayer;//ob der Charalkter ein Spieler ist
	
	
	
	private Skill skills[];//Charakter Faehigkeiten

	
	//Speichert Chatakter-ID als Integer
	private int characterId;
	
	//Index von Status
	public static final int NAME = 1;
	public static final int HP = 2;
	public static final int MP = 3;
	public static final int ISPLAYER = 4;

	
	/**
	 * Konstruktor. Bekommt CharakterID als Paramerter uebergeben.
	 * @param pCharacterId
	 */
	public CharacterData(final int pCharacterId){
		characterId = pCharacterId;
		name  = "***";//default
		hp = 0;//default
		mp = 0;//default
		isPlayer = true;//default
		
	
		
	
	}
	
	private void intializeSkill(){
		skills = new Skill[3];
		
		for(int i = 0; i < skills.length; i++){
			skills[i] = new Skill("***", 0, 1.0);
		}
	}
	
	/**
	 * Gibt ein Array, das alle Faehigkeiten enthaelt, zurueck.
	 * @return
	 */
	public Skill[] getSkill(){
	
		if(skills == null){
			intializeSkill();
		}

		return skills;
	}
	
	/**
	 * Bekommt entsprechenden Charakter-Wert.
	 * @param index
	 * @return
	 */
	public String getValue(int index){
		
		String value = "";
		
		switch (index) {
		case NAME:
			value = getName();
			break;
		case HP:
			value = String.valueOf(getHP());
			break;
		case MP:
			value = String.valueOf(getMP());
			break;
		case ISPLAYER:
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
		case NAME:
			setName(pValue);
			break;
		case HP:
			setHP(Integer.parseInt(pValue));
			break;
		case MP:
			setMP(Integer.parseInt(pValue));
			break;
		case ISPLAYER:
			serIsPlayer(Boolean.parseBoolean(pValue));
			break;

		default:
			break;
		}
	}
	
	
	
	
	//----------ganze Getter und Setter Methode-----------------
	
	
	
	/**
	 * Setter-Methode fuer die Faehigkeitsnamen.
	 * @param pName
	 */
	public void setSkillName(String pSkillName[]) {

		
		if(skills == null) intializeSkill();
		int i = 0;
		for (String value : pSkillName) {
			
			skills[i++].setName(value);
		}


	}

	/**
	 * Setter-Methode fuer die FaehigkeitsMP.
	 * @param pName
	 */
	public void setSkillMP(int pSkillMP[]) {
		if(skills == null) intializeSkill();

		int i = 0;

		for (int value : pSkillMP) {
			skills[i++].setMP(value);

		}
	}

	/**
	 * Setter-Methode fuer die FaehigkeitsSchaden.
	 * @param pName
	 */
	public void setSkillDamage(double pSkillDamage[]) {
		if(skills == null) intializeSkill();

		int i = 0;

		for (double value : pSkillDamage) {
			skills[i++].setDamage(value);

		}
	}
	
	
	
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
	
	
	
	/**
	 * Gibt den Charakternamen dieser Charakterdatei aus.
	 */
	@Override
	public String toString(){
		return CHARACTER_NAME[characterId];
	}
}
