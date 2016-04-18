package domain;

import java.util.Scanner;
import java.util.Vector;

import valueobject.ICharacter;

public class BattleRoom {
	
	Vector<ICharacter> players;
	Vector<ICharacter> enemys;
	ICharacter p01 ;
	ICharacter e01;
	 Scanner scan;
	

	
	
	public BattleRoom(){
		players = new Vector<ICharacter>();
		enemys = new Vector<ICharacter>();
	}
	
	public boolean addPlayer(final ICharacter player){
		return players.add(player);
	}

	public boolean addEnemy(final ICharacter enemy){
		return enemys.add(enemy);
	}
	
	public int numberOfPlayer(){
		return players.size();
	}
	
	public int numberOfEnemy(){
		return enemys.size();
	}
	
	
	public void startBattle(){
		
		
		p01 = players.get(0);
	    e01 = enemys.get(0);

		 int num;
         scan = new Scanner(System.in);

         int round = 0;
         
		 while(p01.isAlive() && e01.isAlive()){
			 
			 round++;
		    System.out.println("-----Round "+round+" ----- \n");

			p01.print();
			
			 num = p01.attack();

			
            if(num != 0){
			
			
			
			System.out.println(e01.getName()+" kriegt "+num+" Schaden! \n");
			e01.setLife(-num);
			e01.print();

			if(!e01.isAlive()){
				  System.out.println("You"
				  		+ " has won!!");
				  return;
			}
			 
		        
}
			

			 num = e01.attack();
			
				
            			 
			 if(num != 0){
		    System.out.print(e01.getName()+" greift an!! w(WEITER) > \n");
		    scan.next();
			
			System.out.println(p01.getName()+" kriegt "+num+" Schaden! \n");
			p01.setLife(-num);
			 }
			p01.print();
			System.out.print("Next round w(WEITER) > \n");
			scan.next();


		}
                scan.close();


             String result = p01.isAlive() ? "You":"ENEMY";
	         System.out.println(result+" has won!!");
	        
	}	
}
