package client.gui.events;
import client.gui.manager.GuiMapHandler;

/**
 * Ben�tigt x u. y Werte der TestFigure, x u. y Werte des Freien Feldes.
 * Muss mit MapHandling kommunizieren, damit die Farben gechanged werden. 
 * e.g fieldSquares[TestFigure.getyCoord()][TestFigure.getxCoord()].setBackground(Color.YELLOW);
 * TestFigure.setCoords(s,i) aus dem fieldSquare Array
 */
public class GuiMoveEvent  extends Thread implements GuiGameEvent {
	
	private GuiMapHandler mapHandler;
	private int clickedCoordinateX, clickedCoordinateY;
	
	public GuiMoveEvent(int clickedCoordinateX, int clickedCoordinateY){
		this.clickedCoordinateX = clickedCoordinateX;
		this.clickedCoordinateY = clickedCoordinateY;
		mapHandler = GuiMapHandler.getInstance();
	}
	@Override
	public void process(){
		//start();
		System.out.println("x move "+clickedCoordinateX);
		System.out.println("y move "+clickedCoordinateY);
		mapHandler.repaintButton(clickedCoordinateX, clickedCoordinateY);
	}
	
	public void run(){
		System.out.println("x move "+clickedCoordinateX);
		System.out.println("y move "+clickedCoordinateY);
		mapHandler.repaintButton(clickedCoordinateX, clickedCoordinateY);
	}
	

}
