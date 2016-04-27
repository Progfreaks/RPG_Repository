package parsistence;

public interface IStatusDefs {

	
	public static int MAX_LEVEL = 50;
	
	public static int STATUS_EXPERIENCE = 0;
	public static int STATUS_ATTACK = 1;
	public static int STATUS_MP = 2;


    public static String STATUS_NAME_EXPERIENCE = "EXPERIENCE";
    public static String STATUS_NAME_ATTACK = "ATTACK";
    public static String STATUS_NAME_MP = "MP";

    
    
    public static int[] STATUS_TABLE = {
    	STATUS_EXPERIENCE,
    	STATUS_ATTACK,
    	STATUS_MP,
    };
    
    public static String[] STATUS_NAME_TABLE = {
    	STATUS_NAME_EXPERIENCE,
    	STATUS_NAME_ATTACK,
    	STATUS_NAME_MP,
    };
}
