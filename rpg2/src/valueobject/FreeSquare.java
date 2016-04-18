package valueobject;

public class FreeSquare {
	private int x_coord, y_coord;
	public FreeSquare(int x, int y){
		this.x_coord = x;
		this.y_coord = y;
	}
	public int getxCoord(){
		return this.x_coord;
	}
	public int getyCoord(){
		return this.y_coord;
	}
	public void setCoords(int x, int y){
		this.x_coord = x;
		this.y_coord = y;
		
	}

}
