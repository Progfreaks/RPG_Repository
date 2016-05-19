package gui.events;
import gui.creater.GuiGameConsole;
import gui.creater.GuiManager;
import domain.DuD;


public class GuiPickUpEvent extends GuiGameEvent {
	
	private int x, y;
	private DuD game;
	private GuiManager guiMgr;
		
	
	
	public GuiPickUpEvent(int x, int y){
		this.x = x;
		this.y = y;
		game = DuD.getGame();
		guiMgr = GuiManager.getInstance();
		guiMgr.repaintButton(x, y);
	}
	
	@Override
	public void process(){
		game.setIndicator(true);
		GuiGameConsole.getInstance().endGoalMessage(game.getPlayer(0));
	}
	
	@Override
	public void run() {
		process();
		
	}

}
