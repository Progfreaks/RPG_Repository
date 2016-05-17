package gui.events;
import gui.GameConsole;
import gui.GuiManager;
import domain.DuD;


public class PickUpEvent extends GameEvent {
	
	private int x, y;
	private DuD game;
	private GuiManager guiMgr;
		
	
	
	public PickUpEvent(int x, int y){
		this.x = x;
		this.y = y;
		game = DuD.getGame();
		guiMgr = GuiManager.getInstance();
		guiMgr.repaintButton(x, y);
	}
	
	@Override
	public void process(){
		game.setIndicator(true);
		GameConsole.getInstance().endGoalMessage(game.getPlayer(0));
	}
	
	@Override
	public void run() {
		process();
		
	}

}
