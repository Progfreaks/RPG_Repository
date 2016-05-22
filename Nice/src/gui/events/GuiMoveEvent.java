package gui.events;
import gui.GuiManager;

/**
 * Ben�tigt x u. y Werte der TestFigure, x u. y Werte des Freien Feldes.
 * Muss mit MapHandling kommunizieren, damit die Farben gechanged werden. 
 * e.g fieldSquares[TestFigure.getyCoord()][TestFigure.getxCoord()].setBackground(Color.YELLOW);
 * TestFigure.setCoords(s,i) aus dem fieldSquare Array
 */
public class GuiMoveEvent extends GuiGameEvent {
	
	private GuiManager guiMgr;
	private int xf, yf;
	
	public GuiMoveEvent(int x, int y){
		
		this.xf = x;
		this.yf = y;
		guiMgr = GuiManager.getInstance();
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
