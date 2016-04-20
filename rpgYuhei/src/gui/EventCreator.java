package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import valueobject.events.*;
import domain.DuD;
import gui.Maprender;


public class EventCreator implements ActionListener {
	private int[][] boardarray;
	private DuD game = null;
	private JButton[][] fieldSquares;
	private int eventType; // Index zum Auswerten des Events
	public EventCreator(int eventType){
		this.game = DuD.getGame(); // Getten des gameobjects
		this.eventType = eventType; // Setten des EventsIndex
	}
	public void actionPerformed(ActionEvent e){
		int x = 0;
		int y = 0;
		fieldSquares = game.getButtonArray();
		for(int i = 0; i < fieldSquares.length; i++){
			for(int s = 0; s < fieldSquares[i].length; s++){
				if(fieldSquares[i][s] == e.getSource()){
					x = s;
					y = i;
				}
			}
		}
		
		
		switch(eventType){
		case 1: GameEvent mevent = new MoveEvent(x, y); game.processEvent(mevent);
				break;
		case 2: GameEvent pevent = new PickUpEvent(); game.processEvent(pevent); break;
		case 3: GameEvent fevent = new FightEvent(); game.processEvent(fevent); break;
		
		}
	}
	
	public void getActionCalls(JButton[][] fieldSquares){ //Zuweisung der ActionListener und Events
		this.boardarray = game.getBoardArray();
		this.fieldSquares = fieldSquares;
		for(int i = 0; i < fieldSquares.length; i++){
			for(int s = 0; s < fieldSquares[i].length; s++){
				switch(boardarray[i][s]){
				case 0: break; //Wall. No ActionEvent
				case 1: fieldSquares[i][s].addActionListener(new EventCreator(1)); break; //Freies Feld. MoveEvent
				case 2: fieldSquares[i][s].addActionListener(new EventCreator(2)); break; //Item. MoveEvent + PickUpEvent
				case 3: fieldSquares[i][s].addActionListener(new EventCreator(3)); break;
				}
			}
		}
	}
}
