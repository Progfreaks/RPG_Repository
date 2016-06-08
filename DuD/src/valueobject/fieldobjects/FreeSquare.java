package valueobject.fieldobjects;

public class FreeSquare extends FieldObjects {
	private int x_coord, y_coord;
	public FreeSquare(int x, int y){
		this.x_coord = x;
		this.y_coord = y;
	}
	public int getXCoord(){
		return this.x_coord;
	}
	public int getYCoord(){
		return this.y_coord;
	}
	public void setCoords(int x, int y){
		this.x_coord = x;
		this.y_coord = y;
		
	}
	public FreeSquare getFieldObject(){
		return this;
	}

}
