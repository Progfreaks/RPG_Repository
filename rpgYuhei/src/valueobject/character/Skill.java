package valueobject.character;

/**
 * Alle Faehigkeiten sind hier gespeichert.
 * 
 * Dies Enumulation bietet alle Faehigkeiten als Konstanz zu definieren.
 * Alle Konstanz besitzt Name, MP und Mehrfach von Schaden.
 * 
 * @author YOU_HEY
 *
 */
public final class Skill {

	

	
	 public enum SkillEnum{
			
			DRIREKT_ANGRIFF("Held","Direkt Angriff",1.0,5),
			DRACHEN_SCHLAG("Held","Drachen Schlag",1.3,10),
			W_KRAFT("Held","W-Kraft",2.0,15),
			FEIER("Magier","Feier",1.0,5),
			MAECHTIGE_MAGIE("Magier","Maechtige Magie",1.3,10),
			ABADAKEDABRA("Magier","Abadakedabra",2.0,15),
			HAUEN("Kobold","Hauen",1.0,5),
			TRETEN("Kobold","Treten",1.3,10),
			K_ATTACK("Kobold","Kobold Attack",2.0,15),
			SYURIKEN("Ninja","SYURIKEN",1.0,5),
			NINJA_ATTACK("Ninja","Ninja Attack",1.3,10),
			NINJA_POWER("Ninja","Ninja Power",2.0,15),
			S1("Spiderman","Spider Strike",1.0,5),
			S2("Spiderman","Web Ball ",1.3,10),
			S3("Spiderman","Spidey Sence",2.0,15);


			
			
			private String skillName;
			private double multi;
			private int mp;
			private String charactetName;
			
			private SkillEnum(String w,String n,double m,int mp){
				this.charactetName = w;
				this.skillName = n;
				this.multi = m;
				this.mp = mp;
			}
			
			public String getName(){
				return charactetName;
			}
			
			public String getSkillName(){
				return skillName;
			}
			
			public int getUseMP(){
				return mp;
			}
			
			public double getMulti(){
				return multi;
			}
			
			
			
			
		}
	 
	 public static SkillEnum[] getSkillByName(String key){
			
		 
		  SkillEnum[] skills = new SkillEnum[3];

			int i = 0;
			
			for(SkillEnum skill : SkillEnum.values()){
				if(skill.getName().equals(key)){
					skills[i++] = skill;
				}
			}
			return skills;
		}
	
}
