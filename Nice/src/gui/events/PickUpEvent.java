package gui.events;
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
		guiMgr = new GuiManager();
		guiMgr.repaintButton(x, y);
	}
	
	@Override
	public void process(){
		game.setIndicator(true);
		game.getConsole().endGoalMessage(game.getPlayer(0));
		//game.recolour(x, y);
		//game.renderChest();
	}
	
	@Override
	public void run() {
		process();
		
	}

}
