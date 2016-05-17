package gui;

import gui.events.FightEvent;
import gui.events.GameEvent;
import gui.events.MoveEvent;
import gui.events.PickUpEvent;
import gui.events.RollEvent;
import gui.events.StartEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Diese Klasse dient der Events-Erzeugung.
 * @author YOU_HEY
 *
 */
public class EventCreator implements ActionListener {

	private int[][] boardMatrix;
	private JButton[][] buttonMatrix;
	private EVENT_TYPE eventType;
	private GuiManager guiMgr;
	private Thread thread;

	// Aufzaehlung fuer Event-Type
	enum EVENT_TYPE {
		DEFAULT, NEW_GAME, ROLL, MOVE, PICKUP, FIGHT;
	}

	/**
	 * Konstruktor
	 * 
	 * @param eventType
	 */
	public EventCreator(EVENT_TYPE eventType) {

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
		boardMatrix = guiMgr.getBoardMatrix();
		buttonMatrix = pButtons;

		for (int i = 0; i < buttonMatrix.length; i++) {

			for (int s = 0; s < buttonMatrix[i].length; s++) {

				switch (boardMatrix[i][s]) {

				case 0:
					break; // Wall. No ActionEvent
				case 1:
					buttonMatrix[i][s].addActionListener(new EventCreator(
							EVENT_TYPE.MOVE));
					break; // Freies Feld. MoveEvent
				case 2:
					buttonMatrix[i][s].addActionListener(new EventCreator(
							EVENT_TYPE.PICKUP));
					break; // Item. MoveEvent + PickUpEvent
				case 3:
					buttonMatrix[i][s].addActionListener(new EventCreator(
							EVENT_TYPE.FIGHT));
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

		StartButton.addActionListener(new EventCreator(EVENT_TYPE.NEW_GAME));


	}

	/**
	 * Setzt das Roll-Event in den Button.
	 * 
	 * @param RollButton
	 */
	public void setRollCall(JButton RollButton) {

		RollButton.addActionListener(new EventCreator(EVENT_TYPE.ROLL));

	}

	//	/**
	//	 * 
	//	 * @param button
	//	 */
	//	public void setEnterCall(JButton button) {
	//		button.addActionListener(new EventCreator(EVENT_TYPE.ENTER));
	//	}

	/**
	 * Wird diese Methode angerufen, wenn einen Button angeklickt wird.
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void actionPerformed(ActionEvent e) {
		// The loop tracks the clicked buttonposition
		int x = 0;
		int y = 0;


		if (eventType == EVENT_TYPE.NEW_GAME) { // Zum abfangen des NewGame


			GameEvent startEvent = new StartEvent(this);
			thread = new Thread(startEvent);
			thread.start();




		} else {

			buttonMatrix = guiMgr.getButtonPanel().getButtonMatrix();
			
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
				System.out.println("MOVE");
				MoveEvent moveEvent = new MoveEvent(x, y);
				moveEvent.process();
				break;

			case PICKUP:

				GameEvent pickEvent = new PickUpEvent(x, y);
				thread = new Thread(pickEvent);
				thread.start();
				break;

			case FIGHT:

				GameEvent fightEvent = new FightEvent(x, y);
				thread = new Thread(fightEvent);
				thread.start();
				break;

			case ROLL:
				RollEvent rollEvent = new RollEvent();
				thread = new Thread(rollEvent);
				thread.start();


				break;



			}
		}// <- ende der if-else Abfrage

	}





}
