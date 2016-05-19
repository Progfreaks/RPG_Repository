package gui.creater;

import gui.events.GuiFightEvent;
import gui.events.GuiGameEvent;
import gui.events.GuiMoveEvent;
import gui.events.GuiPickUpEvent;
import gui.events.GuiRollEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Diese Klasse dient der Events-Erzeugung.
 * @author YOU_HEY
 *
 */
public class GuiEventCreator implements ActionListener {

	private EVENT_TYPE eventType;
	private GuiManager guiMgr;
	
	// Aufzaehlung fuer Event-Type
	enum EVENT_TYPE {
		DEFAULT, NEW_GAME, ROLL, MOVE, PICKUP, FIGHT;
	}

	/**
	 * Konstruktor
	 * 
	 * @param eventType
	 */
	public GuiEventCreator(EVENT_TYPE eventType) {

		this.eventType = eventType; // Setten des Events-Types
		guiMgr = GuiManager.getInstance();

	}

	/**
	 * Setzt die Events in die Buttons.
	 * 
	 * @param pButtons
	 */
	public void setActionCalls(JButton[][] pButtons) {

		//Bekommt das Board-Matrix
		int[][] boardMatrix = guiMgr.getBoardMatrix();
		JButton[][] buttonMatrix = pButtons;

		for (int i = 0; i < buttonMatrix.length; i++) {

			for (int s = 0; s < buttonMatrix[i].length; s++) {

				switch (boardMatrix[i][s]) {

				case 0:
					break; // Wall. No ActionEvent
				case 1:// Freies Feld. MoveEvent
					buttonMatrix[i][s].addActionListener(new GuiEventCreator(EVENT_TYPE.MOVE));
					break; 
				case 2:// Item. MoveEvent + PickUpEvent
					buttonMatrix[i][s].addActionListener(new GuiEventCreator(EVENT_TYPE.PICKUP));
					break; 
				case 3:
					buttonMatrix[i][s].addActionListener(new GuiEventCreator(EVENT_TYPE.FIGHT));
					break;
				}

			}// <-- ende der for Schleife

		}// <- ende der for Schleife

	}

	/**
	 * Setzt das Start-Event in den Button.
	 * 
	 * @param StartButton
	 */
	public void setStartCall(JButton StartButton) {

		StartButton.addActionListener(new GuiEventCreator(EVENT_TYPE.NEW_GAME));


	}

	/**
	 * Setzt das Roll-Event in den Button.
	 * 
	 * @param RollButton
	 */
	public void setRollCall(JButton RollButton) {

		RollButton.addActionListener(new GuiEventCreator(EVENT_TYPE.ROLL));

	}

	

	/**
	 * Wird diese Methode angerufen, wenn einen Button angeklickt wird.
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void actionPerformed(ActionEvent e) {
		// The loop tracks the clicked buttonposition
		int x = 0;
		int y = 0;
		Thread thread;


		if (eventType == EVENT_TYPE.NEW_GAME) { // Zum abfangen des NewGame


//			GuiGameEvent startEvent = new GuiStartEvent(this);
//			 thread = new Thread(startEvent);
//		
//			thread.start();




		} else {

			//Bekommt das Buttonmatrix.
			JButton[][] buttonMatrix = guiMgr.getButtonPanel().getButtonMatrix();
			
			//Bekommt das angeklickte Position
			for (int i = 0; i < buttonMatrix.length; i++) {
				for (int s = 0; s < buttonMatrix[i].length; s++) {
					if (buttonMatrix[i][s] == e.getSource()) {
						x = s;
						y = i;
					}
				}// <- ende der for Schleife
			}// <- ende der for Schleife

			switch (eventType) {

			case MOVE:
				GuiMoveEvent moveEvent = new GuiMoveEvent(x, y);
				moveEvent.process();
				break;

			case PICKUP:

				GuiGameEvent pickEvent = new GuiPickUpEvent(x, y);
				thread = new Thread(pickEvent);
				thread.start();
				break;

			case FIGHT:

				GuiGameEvent fightEvent = new GuiFightEvent(x, y);
				thread = new Thread(fightEvent);
				thread.start();
				break;

			case ROLL:
				GuiRollEvent rollEvent = new GuiRollEvent();
				thread = new Thread(rollEvent);
				thread.start();
				break;

			}
		}// <- ende der if-else Abfrage

	}





}
