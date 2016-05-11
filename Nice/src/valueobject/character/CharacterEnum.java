package valueobject.character;

import valueobject.character.Skill.SkillEnum;


/**
 * Alle Charakter sind hier gespeichert.
 * Dies Enum besitzt alle Charakter als Konstanz.
 * Diese Konstanz hat eigene Werte.
 * @author YOU_HEY
 *
 */
public enum CharacterEnum {
	
	
	//Alle Spiel-Charakter
	Warrior("Held",100,true,Skill.getSkillByName("Held")),
	Wizzard("Magier",100,true,Skill.getSkillByName("Magier")),
	Elf("Kobold",100,true,Skill.getSkillByName("Kobold")),
	Ninja("Ninja",100,true,Skill.getSkillByName("Ninja")),
	Spiderman("Spiderman",100,true,Skill.getSkillByName("Spiderman")),
	Zombie("Zombie",40,false,Skill.getSkillByName("Zombie"));
	
	
	private String name;
	private int life;
	private boolean isPlayer;
	private SkillEnum[] skills;
	
	
	private CharacterEnum(String name,int life,boolean isPlayer,SkillEnum[] skills){
		this.name = name;
		this.life = life;
		this.isPlayer = isPlayer;
		this.skills = skills;
	}
	
	public String getName(){
		return name;
	}
	
	public int getLife(){
		return life;
	}
	
	public boolean getIsPlayer(){
		return isPlayer;
	}
	
	public SkillEnum[] getSkills() {
		return skills;
	}

}
