package client.gui.events;

import server.domain.DuD;

import javax.swing.JOptionPane;

import client.gui.manager.GuiGameConsole;
import client.gui.manager.GuiMapHandler;
import client.net.Facade;
import common.valueobject.Character;


public class GuiCureEvent implements GuiGameEvent{

	private DuD game;
	private GuiMapHandler mapHandler;
	private int clickedCoordinateX;
	private int clickedCoordinateY;
	private Facade single;

	public GuiCureEvent(int clickedCoordinateX,int clickedCoordinateY,Facade single) {
		game =DuD.getGame();
		this.single = single;
		mapHandler = GuiMapHandler.getInstance();
		this.clickedCoordinateX = clickedCoordinateX;
		this.clickedCoordinateY = clickedCoordinateY;
	}

	@Override
	public void process() {
		refreshMapButtonPanel();
		single.cureCharacter();
		GuiGameConsole.getInstance().appendln("Heile fuer alle Player!! :D");
		JOptionPane.showMessageDialog(null,"Heile fuer alle Player!!  wieder volle Lebenspunkte :D", "", JOptionPane.INFORMATION_MESSAGE);


		

	}
	
	private void refreshMapButtonPanel(){
		mapHandler.repaintButton(clickedCoordinateX, clickedCoordinateY);
		mapHandler.removeCureActionCall(clickedCoordinateX, clickedCoordinateY);
		mapHandler.setMoveActionCall(clickedCoordinateX, clickedCoordinateY);
	}
}
