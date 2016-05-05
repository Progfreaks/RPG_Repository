package persistence.character;
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
	public static int SKELETT = 6;

	




	//Charaktername
    public static String NAME_HELD = "Held";
    public static String NAME_MAGIER = "Magier";
    public static String NAME_KOBOLD = "Kobold";
    public static String NAME_NINJA = "Ninja";
    public static String NAME_SPIDERMAN = "Spiderman";
    public static String NAME_ZOMBIE = "Zombie";
    public static String NAME_SKELETT = "Skelett";





    //Array fuer Charakter ID
    public static int[] CharakterID = {
    	HELD,
    	MAGIER,
    	KOBOLD,
    	NINJA,
    	SPIDERMAN,
    	ZOMBIE,
    	SKELETT
    };
    
    //Array fuer Charaktername
    public static String[] CHARACTER_NAME = {
    	NAME_HELD,
    	NAME_MAGIER,
    	NAME_KOBOLD,
    	NAME_NINJA,
    	NAME_SPIDERMAN,
    	NAME_ZOMBIE,
    	NAME_SKELETT
    };
}
