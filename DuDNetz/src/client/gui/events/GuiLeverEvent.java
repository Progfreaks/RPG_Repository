package client.gui.events;
import server.domain.DuD;

import javax.swing.JOptionPane;

import client.gui.manager.GuiGameConsole;
import client.gui.manager.GuiMapHandler;
import client.net.Facade;


public class GuiLeverEvent implements GuiGameEvent {
	
	private GuiMapHandler mapHandler;
	private GuiGameConsole console;
	private int clickedCoordinateX;
	private int clickedCoordinateY;
	private Facade single;
		
	
	
	public GuiLeverEvent(int clickedCoordinateX, int clickedCoordinateY,Facade single){
		console = GuiGameConsole.getInstance();
		this.single = single;
		mapHandler = GuiMapHandler.getInstance();
		this.clickedCoordinateX = clickedCoordinateX;
		this.clickedCoordinateY = clickedCoordinateY;
	}
	
	@Override
	public void process(){
		refreshMapButtonPanel();
		 String ans = single.pullLever();
		JOptionPane.showMessageDialog(null,ans, "", JOptionPane.INFORMATION_MESSAGE);
		console.appendln(ans);
		checkMission();
		
	}
	
	
	private void refreshMapButtonPanel(){
		mapHandler.repaintButton(clickedCoordinateX, clickedCoordinateY);
		mapHandler.removeCureActionCall(clickedCoordinateX, clickedCoordinateY);
		mapHandler.setMoveActionCall(clickedCoordinateX, clickedCoordinateY);
	}
	
	private void checkMission(){
		if(single.isAllEnemyDead()&&single.isAllLevePulled()){
			mapHandler.placeBoss();//end gegner platieren
			console.appendln("Endgegner ist aufgetreten!!!!!!\n");
		}
	}
	

}
