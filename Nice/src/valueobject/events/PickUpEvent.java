package valueobject.events;
import domain.DuD;
import valueobject.PlayerArray;
import domain.CentralSave;


public class PickUpEvent extends GameEvent {
	private int x, y;
	private DuD game;
		
	
	@Override
	public void process(){
		System.out.println("PickUp, der Picknicker von Leibnitz");
		game.setIndicator(true);
		CentralSave.console.endGoalMessage(PlayerArray.getPlayer(0));
		game.recolour(x, y);
		game.renderChest();
	}
	public PickUpEvent(int x, int y){
		this.x = x;
		this.y = y;
		game = DuD.getGame();
	}

}
