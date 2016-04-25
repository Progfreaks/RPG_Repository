package valueobject.events;
import valueobject.fieldobjects.*;
import domain.DuD;

/**
 * Ben�tigt x u. y Werte der TestFigure, x u. y Werte des Freien Feldes.
 * Muss mit MapHandling kommunizieren, damit die Farben gechanged werden. 
 * e.g fieldSquares[TestFigure.getyCoord()][TestFigure.getxCoord()].setBackground(Color.YELLOW);
 * TestFigure.setCoords(s,i) aus dem fieldSquare Array
 */
public class MoveEvent extends GameEvent {
	private static DuD game;
	private TestFigure player;
	//private FieldObjects[][] fieldObjects;
	private int xf, yf;
	public MoveEvent(int x, int y){
		this.xf = x;
		this.yf = y;
		game = DuD.getGame();
		//this.fieldObjects = game.getFieldObjects();
		//this.player = game.getPlayer();
	}
	@Override
	public void process(){
		game.recolour(xf, yf);
		
	}

}
