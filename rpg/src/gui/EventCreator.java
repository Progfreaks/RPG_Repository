package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import valueobject.events.FightEvent;
import valueobject.events.GameEvent;
import domain.DuD;

public class EventCreator implements ActionListener {
	private int[][] boardarray;
	private DuD game;
	private JButton[][] fieldSquares;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		GameEvent event = new FightEvent();
		game.getGame().processEvent(event);
		
	}
	public void getActionCalls(JButton[][] fieldSquares){ //Zuweisung der ActionListener und Events
		this.boardarray = game.getBoardArray();
		this.fieldSquares = fieldSquares;
		for(int i = 0; i < fieldSquares.length; i++){
			for(int s = 0; s < fieldSquares[i].length; s++){
				switch(boardarray[i][s]){
				case 0: break; //Wall. No ActionEvent
				case 1: break; //Freies Feld. MoveEvent
				case 2: break; //Item. MoveEvent + PickUpEvent
				case 3: fieldSquares[i][s].addActionLister
				}
			}
		}
	}
}
