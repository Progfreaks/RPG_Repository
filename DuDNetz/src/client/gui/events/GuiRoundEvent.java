package client.gui.events;

import java.net.Socket;

import common.valueobject.Character;

public class GuiRoundEvent  extends Thread implements GuiGameEvent {
	Character character;
	Socket socket;
	
	 public GuiRoundEvent(Character c) {
		character = c;
		
	}

	@Override
	public void process() {
		start();		
	}
	
	public void run(){
		
		System.out.println("(GuiRoundEvent) (run)\n");
		//GuiGameConsole.getInstance().diceForMove(character,RoundCount.getRoundCount());
		//GuiGameConsole.getInstance().appendln("hi");
	}

}
