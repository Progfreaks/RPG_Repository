package valueobject;
//mit Interface kann Upcast(Interface als Parameter übergeben)

public interface ICharacter {

	
	public String getName();
	
	public void setName(final String name);//mit final wird der Parameter nicht mehr geändert.
	
	public int getLife();
	
	public void setLife(final int life);
	
	public int getLevel();
	
	public void setLevel(final int level);
	
	public int getExPoint();
	
	public void setExPoint(final int exPoint);
	
	public boolean isAlive();
	
	public void setAlive(final boolean param);
	
	public boolean isPlayer();
	
	public void setIsPlayer(final boolean param);
	
	public int getMaxLife();

	public void print();

	public int attack();
	
}
