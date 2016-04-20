package valueobject;

import valueobject.character.Character;
import valueobject.character.Skill;

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
		
		print("Kommando auswaehlen\n");
		print(" 1 : Direkt Angriff (5 MP : Damage*1) \n 2 : Drachen Schlag (10MP : Damage*1.2)"
				+ "\n 3 : W-Kraft (15MP : Damage*1.5) \n 4 : Aufladen");
		
		int damage = 0;
		
		switch (getConsoleInput()) {
		
		case "1": checkMP(ch,5) ; damage = subSelectCommand("Direkt Angriff", ch); ch.setMP(-5);break;
		case "2": checkMP(ch,10) ; damage = subSelectCommand("Drachen Schlag", ch); ch.setMP(-10);break;
		case "3": checkMP(ch,15) ; damage = subSelectCommand("W-Kraft", ch); ch.setMP(-15);break;
		case "4": print("Aufladen\n");	ch.setMP(Dice.diceForMP(ch)); break;
			

	}
		return damage;
		
	}
	
	/**
	 * Hilfsmethode fuer selectCommand()
	 * @param ch
	 */
	public static boolean checkMP(final Character ch,final int usedMP){
		if(ch.getMP() < usedMP) {
			print("Keinen MP...\n");
			selectCommandMessage(ch);
			return false;
		}
		return true;
	}
	
	/**
	 * Hilfsmethode von selectCommand()
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
						
			print(c02.getName()+" kriegt "+num+" Schaden! "+c01.getLife()+"/"+
			c01.getMaxLife()+" -> "+(c01.getLife()-num)+"/"+
					c01.getMaxLife()+" (ENTER)\n");
			getConsoleInput();
			c02.setLife(-num);

			if(!c02.isAlive()){
				  System.out.println("You"
				  		+ " has won!!");
				  return;
			}
                                	} 
            
			c02.print();

            
			 num = c02.attack();
			           			 
			 if(num != 0){
		    print(c02.getName()+" greift an!! (ENTER) > \n");
		    getConsoleInput();
			
			print(c01.getName()+" kriegt "+num+" Schaden! "+c01.getLife()+"/"+
			c01.getMaxLife()+" -> "+(c01.getLife()-num)+"/"+
					c01.getMaxLife()+" (ENTER)\n");
			getConsoleInput();
			c01.setLife(-num);
			 }
			c01.print();
			print("Naechste Runde (ENTER) > \n");
		    getConsoleInput();
		}

            String result = c01.isAlive() ? "You":"ENEMY";
	       print(result+" has won!!");
	        
	}	
	
	public static String chooseCharacterMessage(){
		
		print("Willkommen zu unserem Duengeon!!\n"
				+ "\nCharakter auswaehlen\n"
				+ "\n1 : Held \n2 : Magier \n3 : Kobold \n");
		
		String number = getConsoleInput();
		switch(number){
		case "1": print("\nLos geht's mit dem Held!!\n");; break;
		case "2": print("\nLos geht's mit dem Magier!!\n");; break;
		case "3": print("\nLos geht's mit dem kobold!!\n");; break;

		}
		
		
		
		return number;
		
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
	public static int totalDamageMessage(final int d01,final int d02){
		int totalDamage = (d01*d02);
		print("Der Schaden ist "+ d01+" * "+d02+" = "+totalDamage+"\n");			
        return totalDamage; 
	}
}



