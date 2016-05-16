package gui.events;
import gui.GuiManager;

/**
 * Ben�tigt x u. y Werte der TestFigure, x u. y Werte des Freien Feldes.
 * Muss mit MapHandling kommunizieren, damit die Farben gechanged werden. 
 * e.g fieldSquares[TestFigure.getyCoord()][TestFigure.getxCoord()].setBackground(Color.YELLOW);
 * TestFigure.setCoords(s,i) aus dem fieldSquare Array
 */
public class MoveEvent extends GameEvent {
	
	private GuiManager guiMgr;
	private int xf, yf;
	
	public MoveEvent(int x, int y){
		
		this.xf = x;
		this.yf = y;
		guiMgr = new GuiManager();
	}
	@Override
	public void process(){
		
		guiMgr.repaintButton(xf, yf);

		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
