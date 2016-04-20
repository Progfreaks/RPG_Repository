package valueobject.character;

/**
 * Dies Enumulation bietet alle Faehigkeiten als Konstanz zu definieren.
 * Alle Konstanz besitzt MP und Mehrfach von Schaden.
 * 
 * @author YOU_HEY
 *
 */
public enum Skill {
	
	DirektAngriff(5,1.0,"Held"),
	DrachenSchlag(10,1.2,"Held"),
	WKraft(15,1.5,"Held");
	
	
	private int multiple;
	private double mp;
	private String id;
	
	Skill(int multiple,double mp,String id){
		this.multiple = multiple;
		this.mp = mp;
		this .id = id;
	}
	
	public int getMultiple(){
		return multiple;
	}
	
	public double getMP(){
		return mp;
	}
	
	
}
