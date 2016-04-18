package valueobject;

import java.util.Random;
import java.util.Scanner;

public class Dice {

	
	
	

	
		
		Random random = new Random();
	    Scanner scan = new Scanner(System.in);
	    

		public int shakeDice(final ICharacter ch){
			
			
			int damage=0;
			//boolean failed = false;

			String  message; 
			
	       message = ch.isPlayer() ? "Wuefeln (1/2) w(WEITER) >" : ch.getName()+" wuefelt w(WEITER)) >";
	       System.out.print(message);
		   scan.next();
		   
	       int diceNum01 = random.nextInt(5)+1;
		   if(diceNum01 == 1){
				System.out.println("\nDie Nummer ist 1. Der Angriff ist fehlgeschlagen ..... \n");
				//failed = true;
				return 0;

		   }else{
			System.out.println("\nDie Nummer ist "+ diceNum01+"\n");
		   }
		   
			 message = ch.isPlayer() ? "Wuefeln (2/2) w(WEITER)" : ch.getName()+
					 " wuefelt (2/2) w(WEITER) >";
		       System.out.print(message);
		   scan.next();
		   int diceNum02 = random.nextInt(5)+1;
		   if(diceNum02 == 1){
				System.out.println("\nDie Nummer ist 1. Der Angriff ist fehlgeschlagen..... \n");
				return 0;

		   }else{
			System.out.println("\nDie Nummer ist "+ diceNum02+"\n");
		   }
		   
		    
		   damage = (diceNum01*diceNum02);
		   
		   
		 
			System.out.println("Der Schaden ist "+ diceNum01+" * "+diceNum02+" = "+damage+"\n");

		   
		   
		   
		   return damage;
		}
		
	}

	

