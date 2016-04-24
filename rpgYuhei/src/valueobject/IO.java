package valueobject;

import valueobject.character.Character;
import valueobject.character.CharacterEnum;
import valueobject.character.Skill.SkillEnum;

import java.io.*;




/**
 * 
 * @author YOU_HEY
 * 
 * Klasse für die Konsole-Ausgabe und Eingabe
 *
 */
public final class IO {
	
	//counter Variable fuer requestDice()
	static int count01 = 1;
	static int count02 = 1;
	
	//wird das Mehrfach vom Schaden gespeichert.
	static double multi = 1;
	
	//	//wird der Name der Faehigkeit gespeichert.

	static String skillName ;
	
	/**
	 * Gibt die Konsole-Eingabe zurück.
	 * @return Konsole-Eingabe
	 */
	public static String getConsoleInput(){
		

	    String select = "";
	    
	       BufferedReader	in = new BufferedReader(new InputStreamReader(System.in));
	    	try {
	    		select = in.readLine();
	    	} catch (IOException e) {
	    		
	    		e.printStackTrace();
	    	}
	    	
	    	return select;
	    }
	
	/**
	 * Eine einfache print-Methode
	 * @param param  auf der Konsole auszugebende String
	 */
	private static void print(final String param){
		System.out.println(param);

	}
	
	
	/**
	 * 
	 * Wählt eine Fähigkeit aus und gibt entsprechender Schaden zurück.
	 * @param character 
	 * @return Schaden
	 */
	public static int selectCommandMessage(final Character ch){
		
		
		boolean check = true;
		int damage = 0;

		//solange es genug MP bleibt
		while(check){
			
		print("Kommando auswaehlen\n");
		
		int i = 1;
		SkillEnum[] skills = ch.getSkills() ;

		
		for(SkillEnum skill : skills){
			
			print((i++)+" : "+skill.getSkillName()+" /"+skill.getUseMP()+"MP/Schaden*"+skill.getMulti());
		}
		print("4 : Aufladen\n");
		
		
		
		int command = Integer.parseInt(getConsoleInput());
		if(command != 4){
			
		int useMP = skills[command-1].getUseMP(); 

		 skillName = skills[command-1].getSkillName();
		
		//speichert das Mehrfach
		multi = skills[command-1].getMulti();
		
		if(checkMP(ch,useMP)){
			
		check = false ; 
		damage = subSelectCommand(skillName, ch); 
		ch.setMP(-useMP);
		
		}
		}else{
			print("Aufladen\n");	ch.setMP(Dice.diceForMP(ch)); 
			check = false;
		}
		
		}
		return damage;
		
	}
	
	/**
	 * Hilfsmethode fuer selectCommandMessage()
	 * @param ch
	 */
	public static boolean checkMP(final Character ch,final int usedMP){
		
		if(ch.getMP() < usedMP) {
			
			print("Keinen MP.....\n");
			return false;
		}
		return true;
	}
	
	/**
	 * Hilfsmethode von selectCommandMessage()
	 * @param skill
	 */
	private static int  subSelectCommand(final String skill,final Character ch){
		print(skill+" \n");  
		int damage = Dice.diceForAtk(ch);
		if(damage != 0){
		print("Angreifen? j(Ja) >");
		getConsoleInput();				
		}
		
		return damage;
	}
	
	
	/**
	 * Gibt den Verlauf des Kampfs aus.
	 * @param c01
	 * @param c02
	 */
	public static void roundMessage(final Character c01,final Character c02){

		 int num;
         int round = 0; 
         
		 while(c01.isAlive() && c02.isAlive()){
			 
			round++;
		    print("-----Runde "+round+" ----- (ENTER)\n");
		    getConsoleInput();
			c01.print();
			
			 num = c01.attack();
			
            if(num != 0){
						
			System.out.print(c02.getName()+" kriegt "+num+" Schaden! "+c02.getLife()+"/"+
			c02.getMaxLife()+" -> ");
			c02.setLife(-num);

			print(c02.getLife()+"/"+
					c02.getMaxLife()+" (ENTER)\n");
			getConsoleInput();

			if(!c02.isAlive()){
				  System.out.println("Du"
				  		+ " hast gewonnen!!");
				  return;
			}
                                	} 
            
			c02.print();

            
			 num = c02.attack();
			           			 
			 if(num != 0){
		    print(c02.getName()+" greift an!! (ENTER) > \n");
		    getConsoleInput();
			
			System.out.print(c01.getName()+" kriegt "+num+" Schaden! "+c01.getLife()+"/"+
			c01.getMaxLife()+" -> ");
			c01.setLife(-num);

			print(c01.getLife()+"/"+
					c01.getMaxLife()+" (ENTER)\n");
			getConsoleInput();
			 }
			c01.print();
			print("Naechste Runde (ENTER) > \n");
		    getConsoleInput();
		}

            String result = c01.isAlive() ? "You":"ENEMY";
	       print(result+" has won!!");
	        
	}	
	
	public static CharacterEnum chooseCharacterMessage(){
		
		print("Willkommen zu unserem Duengeon!!\n"
				+ "\nCharakter auswaehlen\n");
		
		int count = 1;
		for(CharacterEnum ch : CharacterEnum.values()){
			if(ch.getIsPlayer()) print((count++)+" :"+ch.getName());
		}
		
		int number = Integer.parseInt(getConsoleInput());
		CharacterEnum character = null;
		
		 print("\nLos geht's mit dem "+CharacterEnum.values()[number-1].getName()+"!!\n"); character = CharacterEnum.values()[number-1] ;
		
	
		return character;
		
	}
	
	/**
	 * Nachricht für den Wuerfeln.
	 * @param ch
	 */
	public static void diceMessage(final Character ch){
		
		
	       String message = ch.isPlayer() ? "Wuerfeln ("+(count01++)+"/2) (ENTER) >" : ch.getName()+" wuerfelt ("+(count02++)+"/2) (ENTER)) >";
	       print(message);
		   getConsoleInput();
		   if(count01 == 3) count01 = 1;
		   if(count02 == 3) count02 = 1;
			
	}
	/**
	 * Gibt so aus, ob die Augenzahl gueltig ist.
	 * @param num
	 * @return
	 */
	public static int validDiceNumMessage(final int num){
		if(num == 1){
			   
			System.out.println("\nDie Nummer ist 1. Der Angriff ist fehlgeschlagen ..... (ENTER)\n");
			getConsoleInput();
			count01 = 1;
			count02 = 1;
			//failed = true;
			return 0;

	   }else{
		print("\nDie Nummer ist "+ num+" (ENTER)\n");
		getConsoleInput();
		return num;
	   }
		
	}
	
	/**
	 * Gibt die Augenzahl aus.
	 * @param num
	 * @return
	 */
	public static int showNumMessage(final int num){
		print("\nDie Nummer ist "+ num+" (ENTER)\n");
		getConsoleInput();
		return num;
	}
	
	public static int chargeMPMessage(final int mp01,final int mp02){
		int totalMP = (mp01*mp02);
		
		print(mp01+" * "+mp02+" = "+totalMP+" MP ist aufgeladen\n");
		
		return totalMP;
	}
	
	/**
	 * Gibt den Schaden aus.
	 * @param d01
	 * @param d02
	 * @param d
	 */
	public static int totalDamageMessage(final int d01,final int d02,final Character ch ){
		double dTotalDamage = ((double)(d01*d02))*multi;
		int iTotalDamage = (int)dTotalDamage;
		print("Der Schaden ist ("+ d01+" * "+d02+") * "+multi+" = "+iTotalDamage+"\n");
		multi = 1.0;
        return iTotalDamage; 
	}
}



