package valueobject.fieldobjects;


public class TestFigure extends FieldObjects {
public int x_coord, y_coord;
public TestFigure(int x, int y){
	this.x_coord = x;
	this.y_coord = y;
}
public void setCoords(int x, int y){
	this.x_coord = x;
	this.y_coord = y;	
}
public int getXCoord(){
	return this.x_coord; 
}
public int getYCoord(){
	return this.y_coord;
}
public TestFigure getFieldObject(){
	return this;
}

}
