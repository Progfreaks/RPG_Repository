package valueobject;

public class Warrior extends Character{

	public Warrior(final String name,final int life) {
		super(name, life);
		
	}
	
	public int attack(){
		
		int damage = 0;
		
	System.out.println("Kommando auswaehlen \n ");
	System.out.println(" 1 : Direkt Angriff \n 2 : Drachen Schlag \n 3 : Treten \n 4 : Aufladen");

	InputConsole iC = new InputConsole();
	
	switch (iC.getString()) {
	case "1":
		System.out.println("Direkt Angriff \n");
		Dice dice = new Dice();
		damage = dice.shakeDice(this);
		if(damage != 0){
		System.out.print("Attack? y(YES):n(SHAKE AGAIN) >");
		 String str = iC.getString();
		
			while(str.equals("n")){
				damage = dice.shakeDice(this);
				System.out.print("Attack? y(YES):n(SHAKE AGAIN) >");
				  str = iC.getString();
			}
			
		}
			
		

		break;
case "2":
		
		break;
case "3":
	
	break;
case "4":
	
	break;

	default:
		break;
	}
	
	return damage;
	
	}

}
