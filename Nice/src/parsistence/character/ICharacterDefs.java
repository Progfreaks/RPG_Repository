package parsistence.character;

public interface ICharacterDefs {

	
	
	public static int HELD = 0;
	public static int MAGIER = 1;
	public static int KOBOLD = 2;
	public static int NINJA = 3;
	public static int SPIDERMAN = 4;




    public static String NAME_HELD = "Held";
    public static String NAME_MAGIER = "Magier";
    public static String NAME_KOBOLD = "Kobold";
    public static String NAME_NINJA = "Ninja";
    public static String NAME_SPIDERMAN = "Spiderman";



    
    
    public static int[] STATUS_TABLE = {
    	HELD,
    	MAGIER,
    	KOBOLD,
    	NINJA,
    	SPIDERMAN
    };
    
    public static String[] CHARACTER_NAME = {
    	NAME_HELD,
    	NAME_MAGIER,
    	NAME_KOBOLD,
    	NAME_NINJA,
    	NAME_SPIDERMAN
    };
}
