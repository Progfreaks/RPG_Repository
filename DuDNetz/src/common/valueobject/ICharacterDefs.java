package common.valueobject;
/**
 * Dies Interface besitzt Charakter IDs und Charakternamen.
 * @author YOU_HEY
 *
 */
public interface ICharacterDefs {

	
	//Charakter ID
	public static int HELD = 0;
	public static int MAGIER = 1;
	public static int KOBOLD = 2;
	public static int NINJA = 3;
	public static int SPIDERMAN = 4;
	public static int ZOMBIE = 5;
	public static int GHOST = 6;
	public static int MUMMY = 7;
	public static int POT = 8;
	public static int SLEIM = 9;
	public static int DEMON = 10;

	




	//Charaktername
    public static String NAME_HELD = "Held";
    public static String NAME_MAGIER = "Magier";
    public static String NAME_KOBOLD = "Kobold";
    public static String NAME_NINJA = "Ninja";
    public static String NAME_SPIDERMAN = "Spiderman";
    public static String NAME_ZOMBIE = "Zombie";
    public static String NAME_GHOST = "Ghost";
    public static String NAME_MUMMY = "Mummy";
    public static String NAME_POT = "Pot";
    public static String NAME_SLEIM = "Sleim";
    public static String NAME_DEMON = "Demon";




    //Array fuer Charakter ID
    public static int[] CharakterID = {
    	HELD,
    	MAGIER,
    	KOBOLD,
    	NINJA,
    	SPIDERMAN,
    	ZOMBIE,
    	GHOST,
    	MUMMY,
    	POT,
    	SLEIM,
    	DEMON
    	
    };
    
    //Array fuer Charaktername
    public static String[] CHARACTER_NAME = {
    	NAME_HELD,
    	NAME_MAGIER,
    	NAME_KOBOLD,
    	NAME_NINJA,
    	NAME_SPIDERMAN,
    	NAME_ZOMBIE,
    	NAME_GHOST,
    	NAME_MUMMY,
    	NAME_POT,
    	NAME_SLEIM,
    	NAME_DEMON
    };
}
