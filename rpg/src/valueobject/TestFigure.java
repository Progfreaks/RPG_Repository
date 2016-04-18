package valueobject;


public class TestFigure {
public int x_coord, y_coord;
public TestFigure(int x, int y){
	this.x_coord = x;
	this.y_coord = y;
}
public void setCoords(int x, int y){
	this.x_coord = x;
	this.y_coord = y;	
}
public int getxCoord(){
	return this.x_coord; 
}
public int getyCoord(){
	return this.y_coord;
}

}
