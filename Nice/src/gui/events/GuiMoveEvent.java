package gui.events;
import domain.DuD;
import gui.manager.GuiGameConsole;
import gui.manager.GuiMapHandler;

/**
 * Ben�tigt x u. y Werte der TestFigure, x u. y Werte des Freien Feldes.
 * Muss mit MapHandling kommunizieren, damit die Farben gechanged werden. 
 * e.g fieldSquares[TestFigure.getyCoord()][TestFigure.getxCoord()].setBackground(Color.YELLOW);
 * TestFigure.setCoords(s,i) aus dem fieldSquare Array
 */
public class GuiMoveEvent extends GuiGameEvent {
	
	private GuiMapHandler mapHdr;
	private int xf, yf;
	
	public GuiMoveEvent(int x, int y){
		this.xf = x;
		this.yf = y;
		mapHdr = GuiMapHandler.getInstance();
	}
	@Override
	public void process(){
		System.out.println("x move "+xf);
		System.out.println("y move "+yf);
		mapHdr.repaintButton(xf, yf);
		
		//DuD game = DuD.getGame();
		//GuiGameConsole.getInstance().diceForMove(game.getNextPlayer(), game.nextRound());
	}
	@Override
	public void run() {
		process();
		
	}

}
