package valueobject.events;
/**
 * Benötigt x u. y Werte der TestFigure, x u. y Werte des Freien Feldes.
 * Muss mit MapHandling kommunizieren, damit die Farben gechanged werden. 
 * e.g fieldSquares[TestFigure.getyCoord()][TestFigure.getxCoord()].setBackground(Color.YELLOW);
 * TestFigure.setCoords(s,i) aus dem fieldSquare Array
 */
public class MoveEvent extends GameEvent {
	@Override
	public void process(){
		System.out.println("Move");
	}

}
