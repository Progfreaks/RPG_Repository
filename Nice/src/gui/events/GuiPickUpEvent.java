package gui.events;
import gui.manager.GuiGameConsole;
import gui.manager.GuiManager;
import gui.manager.GuiMapHandler;
import domain.DuD;


public class GuiPickUpEvent extends GuiGameEvent implements Runnable {
	
	private int x, y;
	private DuD game;
	private GuiMapHandler mapHdr;
		
	
	
	public GuiPickUpEvent(int x, int y){
		this.x = x;
		this.y = y;
		game = DuD.getGame();
		mapHdr = GuiMapHandler.getInstance();
		mapHdr.repaintButton(x, y);
	}
	
	@Override
	public void process(){
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		//game.setIndicator(true);
		GuiGameConsole.getInstance().endGoalMessage(game.getPlayer());
		
	}

}
