package valueobject;

public abstract class Character implements ICharacter{
	
	private String name;
	
	private int life;
	
	private int level;
	
	private int exPoint;
	
	private boolean isAlive;
	
	private boolean isPlayer;
	
	private final int maxLife;
	
	public  Character(final String name,final int life) {
		
		this.name = name;
		this.life = life;
		this.level = 1;
		this.exPoint = 0;
		this.isAlive = true;
		this.isPlayer = true;
		maxLife = life;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public void setLife(final int life) {
		this.life += life;
		if(this.life <= 0){
			isAlive = false;
		    this.life = 0;
		}
		
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getExPoint() {
		return exPoint;
	}

	@Override
	public void setExPoint(int exPoint) {
	     this.exPoint = exPoint;
	}
	
	@Override
	public boolean isAlive(){
		return isAlive;
	}
	@Override
	public void setAlive(final boolean param){
		this.isAlive = param;
	}
	
	@Override
	public boolean isPlayer(){
		return isPlayer;
	}
	
	@Override
	public void setIsPlayer(final boolean param){
		isPlayer = param;
	}
	
	@Override
	public int getMaxLife(){
		return maxLife;
	}

	@Override
	public void print(){
		System.out.println("Name:"+getName()+" Life:"+getLife()+"/"+getMaxLife()+" Level:"+getLevel()+"\n");
	}
	
	

}
